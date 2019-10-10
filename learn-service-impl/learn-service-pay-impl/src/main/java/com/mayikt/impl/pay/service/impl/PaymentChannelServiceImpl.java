package com.mayikt.impl.pay.service.impl;

import com.mayikt.api.pay.PaymentChannelService;
import com.mayikt.impl.pay.service.dao.ChannelDao;
import com.mayikt.impl.pay.service.entity.ChannelEntity;
import com.unity.core.base.BaseApiService;
import com.unity.core.core.utils.mapper.MapperUtils;
import learn.pay.dto.PaymentChannelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class PaymentChannelServiceImpl extends BaseApiService<List<PaymentChannelDTO>>
		implements PaymentChannelService {
	@Autowired
	private ChannelDao paymentChannelMapper;

	@Override
	public List<PaymentChannelDTO> selectAll() {
		List<ChannelEntity> paymentChannelList = paymentChannelMapper.selectList(null);
		return MapperUtils.mapAsList(paymentChannelList, PaymentChannelDTO.class);
	}

}
