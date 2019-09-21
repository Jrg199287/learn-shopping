package com.mayikt.api.weixin;

import com.alibaba.fastjson.JSONObject;
import com.unity.core.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import learn.member.dto.input.UserLoginInpDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "用户登陆服务接口")
public interface MemberLoginService {
	/**
	 * 用户登陆接口
	 * 
	 * @param userLoginInpDTO
	 * @return
	 */
	@PostMapping("/member/login")
	@ApiOperation(value = "会员用户登陆信息接口")
	BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO);

	/**
	 * 删除登陆token
	 *
	 * @return
	 */
	@PostMapping("/delToken")
	@ApiOperation(value = "删除登陆token")
	BaseResponse<JSONObject> delToken(@RequestParam("token") String token);

}
