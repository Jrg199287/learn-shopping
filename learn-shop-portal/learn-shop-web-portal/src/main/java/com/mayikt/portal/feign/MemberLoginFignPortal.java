
package com.mayikt.portal.feign;
import com.mayikt.api.weixin.MemberLoginService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName : MemberLoginFignPortal  //类名
 * @Description : 登陆fign  //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-09-21 20:19  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */

@FeignClient("app-mayikt-member")
public interface MemberLoginFignPortal extends MemberLoginService {
}

