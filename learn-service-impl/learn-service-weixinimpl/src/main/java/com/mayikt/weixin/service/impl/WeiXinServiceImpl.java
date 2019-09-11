package com.mayikt.weixin.service.impl;

import com.mayikt.api.weixin.WeiXinService;
import com.unity.core.base.BaseApiService;
import com.unity.core.base.BaseResponse;
import learn.entity.core.api.entity.AppEntitys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : WeiXinServiceImpl  //类名
 * @Description : 业务测试  //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-08-27 10:55  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
@RestController
public class WeiXinServiceImpl extends BaseApiService implements WeiXinService {
   /**
    * 测试方法
    * @return
    */
   @Value("${learn_test_name}")
   private String name;
   @Override
   public BaseResponse<AppEntitys> getName() {
      AppEntitys appEntity = new AppEntitys();
      appEntity.setName(name);
      appEntity.setPassword("bbb");
      return setResultSuccess(appEntity);
   }
}
