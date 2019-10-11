package com.mayikt.api.pay;

import com.alibaba.fastjson.JSONObject;
import com.unity.core.base.BaseResponse;
import learn.pay.dto.PayCratePayTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface PayMentTransacService {

	/**
	 * 创建支付令牌
	 * 
	 * @return
	 */
	@PostMapping("/cratePayToken")
	public BaseResponse<JSONObject> cratePayToken(@Validated PayCratePayTokenDto payCratePayTokenDto);
}
