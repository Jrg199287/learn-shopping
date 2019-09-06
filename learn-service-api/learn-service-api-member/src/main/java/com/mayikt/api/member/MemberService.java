package com.mayikt.api.member;

import com.jiaorg.springclouddemo.learnentityapi.weixinentity.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName : MemberService  //类名
 * @Description : 接口测试  //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-08-27 10:50  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
@Api(tags = "会员服务接口")
//@FeignClient(value = "APP-MAYIKT-MEMBER")
public interface MemberService {
    @ApiOperation(value = "根据手机号码查询是否已经存在")
    @GetMapping("/member")
    public AppEntity memberToWeixin();
}
