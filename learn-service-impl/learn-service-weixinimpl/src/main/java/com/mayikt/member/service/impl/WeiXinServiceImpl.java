package com.mayikt.member.service.impl;

import com.jiaorg.springclouddemo.learnentityapi.weixinentity.AppEntity;
import com.mayikt.api.member.WeiXinService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : WeiXinServiceImpl  //类名
 * @Description : 业务测试  //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-08-27 10:55  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
@RestController
public class WeiXinServiceImpl implements WeiXinService {
   /**
    * 测试方法
    * @return
    */
   @Override
   public AppEntity getName() {
      AppEntity appEntity = new AppEntity();
      appEntity.setName("aaaa");
      appEntity.setPassword("bbb");
      return appEntity;
   }
}
