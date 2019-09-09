package com.mayikt.member.service.fign;

import com.mayikt.api.member.WeiXinService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName : MemberServiceimpl  //类名
 * @Description : 会员掉微信  //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-08-27 15:25  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
@FeignClient("app-mayikt-weixin")
public interface MemberServiceimplFign extends WeiXinService {
}
