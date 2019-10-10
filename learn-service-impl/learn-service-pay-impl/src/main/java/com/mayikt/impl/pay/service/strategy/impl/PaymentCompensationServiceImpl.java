package com.mayikt.impl.pay.service.strategy.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.pay.PaymentCompensationService;
import com.mayikt.impl.pay.service.dao.ChannelDao;
import com.mayikt.impl.pay.service.dao.TransactionDao;
import com.mayikt.impl.pay.service.entity.ChannelEntity;
import com.mayikt.impl.pay.service.entity.TransactionEntity;
import com.mayikt.impl.pay.service.factory.CompensationStrategyFactory;
import com.mayikt.impl.pay.service.strategy.PaymentCompensationStrategy;
import com.unity.core.base.BaseApiService;
import com.unity.core.base.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentCompensationServiceImpl extends BaseApiService<JSONObject> implements PaymentCompensationService {
	@Autowired
	private TransactionDao paymentTransactionMapper;
	@Autowired
	private ChannelDao paymentChannelMapper;

	@Override
	public BaseResponse<JSONObject> payMentCompensation(String payMentId) {
		if (StringUtils.isEmpty(payMentId)) {
			return setResultError("payMentId不能为空");
		}
		TransactionEntity paymentTransaction = paymentTransactionMapper.selectByPaymentId(payMentId);
		if (paymentTransaction == null) {
			return setResultError("paymentTransaction为空!");
		}

		// 2.获取所有的渠道重试id
		List<ChannelEntity> paymentChannelList = paymentChannelMapper.selectAll();
		for (ChannelEntity pcl : paymentChannelList) {
			if (pcl != null) {
				return compensationStrategy(paymentTransaction, pcl);
			}
		}

		return setResultError("没有执行重试任务");
	}

	private BaseResponse<JSONObject> compensationStrategy(TransactionEntity paymentTransaction,
														  ChannelEntity paymentChannelEntity) {
		String retryBeanId = paymentChannelEntity.getRetryBeanId();
		PaymentCompensationStrategy paymentCompensationStrategy = CompensationStrategyFactory
				.getPaymentCompensationStrategy(retryBeanId);
		// 3.实现子类重试
		Boolean payMentCompensation = paymentCompensationStrategy.payMentCompensation(paymentTransaction,
				paymentChannelEntity);
		return payMentCompensation ? setResultSuccess("重试成功!") : setResultError("重试失败!");
	}

}
