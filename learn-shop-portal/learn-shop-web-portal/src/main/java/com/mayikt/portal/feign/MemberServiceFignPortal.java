package com.mayikt.portal.feign;

import com.mayikt.api.weixin.MemberRegisterService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName : MemberServiceFignPortal  //类名
 * @Description : fein调用会员接口  //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-09-21 19:16  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
@FeignClient("app-mayikt-member")
public interface MemberServiceFignPortal extends MemberRegisterService {
}
