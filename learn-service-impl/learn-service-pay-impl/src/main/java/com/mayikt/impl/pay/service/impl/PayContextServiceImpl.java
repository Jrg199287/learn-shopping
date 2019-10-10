package com.mayikt.impl.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.pay.PayContextService;
import com.mayikt.api.pay.PayMentTransacInfoService;
import com.mayikt.impl.pay.service.dao.ChannelDao;
import com.mayikt.impl.pay.service.entity.ChannelEntity;
import com.mayikt.impl.pay.service.factory.StrategyFactory;
import com.mayikt.impl.pay.service.strategy.PayStrategy;
import com.unity.core.base.BaseApiService;
import com.unity.core.base.BaseResponse;
import learn.pay.dto.PayMentTransacDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PayContextServiceImpl extends BaseApiService<JSONObject> implements PayContextService {
	@Autowired
	private ChannelDao channelDao;
	@Autowired
	private PayMentTransacInfoService payMentTransacInfoServiceFeign;
	@Override
	public BaseResponse<JSONObject> toPayHtml(String channelId, String payToken) {
		// 1.使用渠道id获取渠道信息 classAddres
		ChannelEntity pymentChannel = channelDao.selectBychannelId(channelId);
		if(pymentChannel==null){
			setResultError("该渠道信息不存在！");
		}
		// 2.使用payToken获取支付参数
		BaseResponse<PayMentTransacDTO> tokenByPayMentTransac = payMentTransacInfoServiceFeign.tokenByPayMentTransac(payToken);
		if (!isSuccess(tokenByPayMentTransac)) {
			return setResultError(tokenByPayMentTransac.getMsg());
		}
		PayMentTransacDTO payMentTransacDTO = tokenByPayMentTransac.getData();
		// 3.执行具体的支付渠道的算法获取html表单数据 策略设计模式 使用java反射机制 执行具体方法
		String classAddres = pymentChannel.getClassAddres();
		PayStrategy payStrategy = StrategyFactory.getPayStrategy(classAddres);
		String payHtml = payStrategy.toPayHtml(pymentChannel, payMentTransacDTO);
		// 4.直接返回html
		JSONObject data = new JSONObject();
		data.put("payHtml", payHtml);
		return setResultSuccess(data);
	}

}
