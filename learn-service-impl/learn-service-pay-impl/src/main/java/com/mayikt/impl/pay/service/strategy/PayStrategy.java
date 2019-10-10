package com.mayikt.impl.pay.service.strategy;


import com.mayikt.impl.pay.service.entity.ChannelEntity;
import learn.pay.dto.PayMentTransacDTO;

public interface PayStrategy {

	/**
	 * 
	 * @param pymentChannel
	 *            渠道参数
	 * @param payMentTransacDTO
	 *            支付参数
	 * @return
	 */
	public String toPayHtml(ChannelEntity pymentChannel, PayMentTransacDTO payMentTransacDTO);

}