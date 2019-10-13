package com.mayikt.impl.spike.service.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.spike.OrderSeckillService;
import com.mayikt.impl.spike.service.service.mapper.OrderMapper;
import com.mayikt.impl.spike.service.service.mapper.entity.OrderEntity;
import com.unity.core.base.BaseApiService;
import com.unity.core.base.BaseResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderSeckillServiceImpl extends BaseApiService<JSONObject> implements OrderSeckillService {
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public BaseResponse<JSONObject> getOrder(String phone, Long seckillId) {
		if (StringUtils.isEmpty(phone)) {
			return setResultError("手机号码不能为空!");
		}
		if (seckillId == null) {
			return setResultError("商品库存id不能为空!");
		}
		OrderEntity orderEntity = orderMapper.findByOrder(phone, seckillId);
		if (orderEntity == null) {
			return setResultError("正在排队中.....");
		}
		return setResultSuccess("恭喜你秒杀成功!");
	}

}
