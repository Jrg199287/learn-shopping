package com.mayikt.member.service.impl;


import com.mayikt.api.weixin.MemberService;
import learn.entity.core.api.entity.UserEntity;
import com.unity.core.base.BaseApiService;
import com.unity.core.base.BaseResponse;
import com.unity.core.constants.Constants;
import com.mayikt.member.service.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
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
public class MemberServiceImpl extends BaseApiService<UserEntity> implements MemberService {
   @Autowired
   private UserMapper userMapper;

   @Override
   public BaseResponse<UserEntity> existMobile(String mobile) {
      // 1.验证参数
      if (StringUtils.isEmpty(mobile)) {
         return setResultError("手机号码不能为空!");
      }
      UserEntity userEntity = userMapper.existMobile(mobile);
      if (userEntity == null) {
         return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_202, "用户不存在");
      }
      // 注意需要将敏感数据进行脱敏
      userEntity.setPassword(null);
      return setResultSuccess(userEntity);
   }

}

