package com.mayikt.member.service.impl;

import com.jiaorg.springclouddemo.learnentityapi.weixinentity.AppEntity;
import com.mayikt.api.member.MemberService;
import com.mayikt.member.service.fign.MemberServiceimplFign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : WeiXinServiceImpl  //类名
 * @Description : 业务测试  //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-08-27 10:55  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
@RestController
public class MemberServiceImpl implements MemberService {
   @Autowired
   private MemberServiceimplFign serviceimplFign;
   @Override
   public AppEntity memberToWeixin() {
      return serviceimplFign.getName();
   }
}
