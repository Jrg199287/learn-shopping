package com.mayikt.impl.pay.service.callback.template;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.impl.pay.service.config.AsyncTaskConfig;
import com.mayikt.impl.pay.service.constant.PayConstant;
import com.mayikt.impl.pay.service.dao.TransactionLogDao;
import com.mayikt.impl.pay.service.entity.TransactionEntity;
import com.mayikt.impl.pay.service.entity.TransactionLogEntity;
import com.mayikt.impl.pay.service.producer.IntegralProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @description: 使用模版方法重构异步回调代码
 */
@Slf4j
@Component
public abstract class AbstractPayCallbackTemplate{
	@Autowired
	private TransactionLogDao paymentTransactionLogsMapper;
	@Autowired
	private AsyncTaskConfig asyncTaskConfig;
	@Autowired
	private IntegralProducer integralProducer;

	/**
	 * 获取所有请求的参数，封装成Map集合 并且验证是否被篡改
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	public abstract Map<String, String> verifySignature(HttpServletRequest req, HttpServletResponse resp);

	/**
	 * 异步回调执行业务逻辑
	 * 
	 * @param verifySignature
	 */
	@Transactional
	public abstract String asyncService(Map<String, String> verifySignature);

	public abstract String failResult();

	public abstract String successResult();

	/**
	 * *1. 将报文数据存放到es <br>
	 * 1. 验证报文参数<br>
	 * 2. 将日志根据支付id存放到数据库中<br>
	 * 3. 执行的异步回调业务逻辑<br>
	 * 
	 */
	@Transactional
	public String asyncCallBack(HttpServletRequest req, HttpServletResponse resp) {
		// 1. 验证报文参数 相同点 获取所有的请求参数封装成为map集合 并且进行参数验证
		Map<String, String> verifySignature = verifySignature(req, resp);
		// 2.将日志根据支付id存放到数据库中
		String paymentId = verifySignature.get("paymentId");
		if (StringUtils.isEmpty(paymentId)) {
			return failResult();
		}
		log.info(">>>service-1");
		// 3.采用异步形式写入日志到数据库中
		asyncTaskConfig.getAsyncExecutor().execute(new LogThread(paymentId, verifySignature));
		log.info(">>>service-4");


		String result = verifySignature.get(PayConstant.RESULT_NAME);
		// 4.201报文验证签名失败
		if (result.equals(PayConstant.RESULT_PAYCODE_201)) {
			return failResult();
		}
		// 5.执行的异步回调业务逻辑
		return asyncService(verifySignature);
	}

	/**
	 *
	 *
	 * 采用多线程技术或者MQ形式进行存放到数据库中
	 *
	 * @param paymentId
	 * @param verifySignature
	 */
	private void payLog(String paymentId, Map<String, String> verifySignature) {
		log.info(">>paymentId:{paymentId},verifySignature:{}", verifySignature);
		TransactionLogEntity paymentTransactionLogEntity = new TransactionLogEntity();
		paymentTransactionLogEntity.setAsyncLog(verifySignature.toString());
		paymentTransactionLogEntity.setTransactionId(paymentId);
		paymentTransactionLogsMapper.isnertEntity(paymentTransactionLogEntity);
	}


	/**
	 * 增加积分
	 *
	 * @param paymentTransaction
	 */
	@Async
	protected void addIntegral(TransactionEntity paymentTransaction) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("paymentId", paymentTransaction.getPaymentId());
		jsonObject.put("userId", paymentTransaction.getUserId());
		jsonObject.put("integral", 100);
		jsonObject.put("paymentChannel", paymentTransaction.getChannelName());
		integralProducer.send(jsonObject);
	}






	class LogThread implements Runnable {
		private String paymentId;
		private Map<String, String> verifySignature;

		public LogThread(String paymentId, Map<String, String> verifySignature) {
			this.paymentId = paymentId;
			this.verifySignature = verifySignature;
		}

		@Override
		public void run() {
			log.info(">>>>异步线程开始...service-2");
			payLog(paymentId, verifySignature);
			log.info(">>>>异步线程结束...service-3");
		}

	}

}
