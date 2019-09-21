package com.mayikt.api.weixin;

import com.unity.core.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import learn.member.dto.output.UserOutDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName : MemberService  //类名
 * @Description : 接口测试  //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-08-27 10:50  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
@Api(tags = "会员服务接口")
public interface MemberService {
    /**
     * 根据手机号码查询是否已经存在,如果存在返回当前用户信息
     *
     * @param mobile
     * @return
     */
    @ApiOperation(value = "根据手机号码查询是否已经存在")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "mobile", dataType = "String", required = true, value = "用户手机号码"), })
    @PostMapping("/existMobile")
    BaseResponse<UserOutDTO> existMobile(@RequestParam("mobile") String mobile);

    /**
     * 根据token查询用户信息
     *
     * @param token
     * @return
     */
    @GetMapping("/getUserInfo")
    @ApiOperation(value = "/getUserInfo")
    BaseResponse<UserOutDTO> getInfo(@RequestParam("token") String token);

}

