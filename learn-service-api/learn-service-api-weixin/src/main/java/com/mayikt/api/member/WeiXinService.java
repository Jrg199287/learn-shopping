package com.mayikt.api.member;

import com.jiaorg.springclouddemo.learnentityapi.weixinentity.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @ClassName : WeiXinService  //类名
 * @Description : 接口测试  //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-08-27 10:50  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
@Api(tags = "微信注册码验证码接口")
public interface WeiXinService {
    @GetMapping("/test")
    @ApiOperation(value = "根据手机号码验证码token是否正确")
  /*  @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "phone", dataType = "String", required = true, value = "用户手机号码"),
            @ApiImplicitParam(paramType = "query", name = "weixinCode", dataType = "String", required = true, value = "微信注册码") })*/
    public AppEntity getName();
}
