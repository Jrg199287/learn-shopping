package com.mayikt.impl.pay.service.strategy;

import com.mayikt.impl.pay.service.entity.ChannelEntity;
import com.mayikt.impl.pay.service.entity.TransactionEntity;

public interface PaymentCompensationStrategy {
	/**
	 * 渠道名称
	 * 
	 * @param
	 * @return
	 */
	public Boolean payMentCompensation(TransactionEntity paymentTransaction, ChannelEntity paymentChanne);
}
