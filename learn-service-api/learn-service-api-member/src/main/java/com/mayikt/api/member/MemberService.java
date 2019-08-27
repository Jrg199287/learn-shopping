package com.mayikt.api.member;

import com.jiaorg.springclouddemo.learnentityapi.weixinentity.AppEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName : MemberService  //类名
 * @Description : 接口测试  //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-08-27 10:50  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
public interface MemberService {
    @GetMapping("/member")
    public AppEntity memberToWeixin();
}
