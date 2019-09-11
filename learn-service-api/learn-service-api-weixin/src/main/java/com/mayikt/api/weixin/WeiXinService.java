package com.mayikt.api.weixin;

import com.unity.core.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import learn.entity.core.api.entity.AppEntitys;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @ClassName : WeiXinService  //类名
 * @Description : 接口测试  //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-08-27 10:50  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
@Api(tags = "微信测试接口")
public interface WeiXinService {
    @GetMapping("/login")
    @ApiOperation(value = "测试正确")
    public BaseResponse<AppEntitys> getName();
}
