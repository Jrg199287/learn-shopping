package com.mayikt.impl.pay.service.integral;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class IntegralConsumer {
	@Autowired
	private IntegralMapper integralMapper;

	@RabbitListener(queues = "integral_queue")
	public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
		try {
			String messageId = message.getMessageProperties().getMessageId();
			String msg = new String(message.getBody(), "UTF-8");
			log.info(">>>messageId:{},msg:{}", messageId, msg);
			JSONObject jsonObject = JSONObject.parseObject(msg);
			String paymentId = jsonObject.getString("paymentId");
			if (StringUtils.isEmpty(paymentId)) {
				log.error(">>>>支付id不能为空 paymentId:{}", paymentId);
				basicNack(message, channel);
				return;
			}
			// 使用paymentId查询是否已经增加过积分
			IntegralEntity resultIntegralEntity = integralMapper.findIntegral(paymentId);
			if (resultIntegralEntity != null) {
				log.error(">>>>paymentId:{}已经增加过积分", paymentId);
				basicNack(message, channel);
				return;
			}
			Integer userId = jsonObject.getInteger("userId");
			if (userId == null) {
				log.error(">>>>paymentId:{},对应的用户userId参数为空", paymentId);
				basicNack(message, channel);
				return;
			}
			Long integral = jsonObject.getLong("integral");
			if (integral == null) {
				log.error(">>>>paymentId:{},对应的用户integral参数为空", integral);
				return;
			}
			IntegralEntity integralEntity = new IntegralEntity();
			integralEntity.setPaymentId(paymentId);
			integralEntity.setIntegral(integral);
			integralEntity.setUserId(userId);
			integralEntity.setAvailability(1);
			int insertIntegral = integralMapper.insertIntegral(integralEntity);
			if (insertIntegral > 0) {
				// 手动签收消息,通知mq服务器端删除该消息
				basicNack(message, channel);
			}
		} catch (Exception e) {
			log.error(">>>>ERROR MSG:", e.getMessage());
			basicNack(message, channel);
		}

	}

	private void basicNack(Message message, Channel channel) throws IOException {
		channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);

	}
}
