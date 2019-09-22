package com.mayikt.api.weixin;

import com.alibaba.fastjson.JSONObject;
import com.unity.core.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import learn.member.dto.input.UserInpDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "会员注册接口")
public interface MemberRegisterService {
	/**
	 * 用户注册接口
	 * 
	 * @param userEntity
     * @param  registCode
	 * @return
	 */
	@PostMapping("/member/register")
	@ApiOperation(value = "会员用户注册信息接口")
	BaseResponse<JSONObject> register(@RequestBody UserInpDTO userEntity,
									  @RequestParam("registCode") String registCode);

}
