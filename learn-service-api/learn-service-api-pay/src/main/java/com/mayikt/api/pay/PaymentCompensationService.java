package com.mayikt.api.pay;

import com.alibaba.fastjson.JSONObject;
import com.unity.core.base.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;

public interface PaymentCompensationService {

	/**
	 * 根据payMentId查询支付信息
	 * 
	 * 
	 * @param payMentId
	 * @return
	 */
	@GetMapping("/payMentCompensation")
	public BaseResponse<JSONObject> payMentCompensation(String payMentId);

}
