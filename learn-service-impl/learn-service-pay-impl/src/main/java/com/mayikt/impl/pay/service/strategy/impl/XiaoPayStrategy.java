package com.mayikt.impl.pay.service.strategy.impl;


import com.mayikt.impl.pay.service.entity.ChannelEntity;
import com.mayikt.impl.pay.service.strategy.PayStrategy;
import learn.pay.dto.PayMentTransacDTO;

public class XiaoPayStrategy implements PayStrategy {

	@Override
	public String toPayHtml(ChannelEntity pymentChannel, PayMentTransacDTO payMentTransacDTO) {

		return "小米支付from表单提交";
	}
	//com.mayikt.pay.strategy.impl.XiaoPayStrategy

}
