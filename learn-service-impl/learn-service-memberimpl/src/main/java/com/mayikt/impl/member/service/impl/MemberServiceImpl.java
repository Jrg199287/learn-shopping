package com.mayikt.impl.member.service.impl;


import com.mayikt.api.weixin.MemberService;
import com.mayikt.impl.member.service.dao.UserDao;
import com.mayikt.impl.member.service.entity.UserEntity;
import com.unity.core.base.BaseApiService;
import com.unity.core.base.BaseResponse;
import com.unity.core.constants.Constants;
import com.mayikt.impl.member.service.dao.UserMapper;
import com.unity.core.core.utils.GenerateToken;
import com.unity.core.core.utils.MD5Util;
import com.unity.core.core.utils.MiteBeanUtils;
import com.unity.core.core.utils.TypeCastHelper;
import learn.member.dto.input.UserInpDTO;
import learn.member.dto.input.UserLoginInpDTO;
import learn.member.dto.output.UserOutDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : WeiXinServiceImpl  //类名
 * @Description : 业务测试  //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-08-27 10:55  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
@RestController
@Service
public class MemberServiceImpl extends BaseApiService<UserOutDTO> implements MemberService {
   @Autowired
   private UserMapper userMapper;
   @Autowired
   private UserDao userDao;
   @Autowired
   private GenerateToken generateToken;
   @Override
   public BaseResponse<UserOutDTO> existMobile(String mobile) {
      // 1.验证参数
      if (StringUtils.isEmpty(mobile)) {
         return setResultError("手机号码不能为空!");
      }
      UserInpDTO userEntity = userMapper.existMobile(mobile);

      if (userEntity == null) {
         return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_202, "用户不存在");
      }
      // 注意需要将敏感数据进行脱敏
      UserOutDTO userOutDTO = MiteBeanUtils.E2T(userEntity,UserOutDTO.class);
      return setResultSuccess(userOutDTO);
   }

   @Override
   public BaseResponse<UserOutDTO> getInfo(String token) {
      // 1.参数验证
      if (StringUtils.isEmpty(token)) {
         return setResultError("token不能为空!");
      }
      // 2.使用token向redis中查询userId
      String redisValue = generateToken.getToken(token);
      if (StringUtils.isEmpty(redisValue)) {
         return setResultError("token已经失效或者不正确");
      }
      Long userId = TypeCastHelper.toLong(redisValue);
      // 3.根据userId查询用户信息
      UserInpDTO userDo = userMapper.findByUserId(userId);
      if (userDo == null) {
         return setResultError("用户信息不存在!");
      }
      // 4.将Do转换为Dto
      UserOutDTO doToDto = MiteBeanUtils.E2T(userDo, UserOutDTO.class);
      return setResultSuccess(doToDto);
   }

   @Override
   public BaseResponse<UserOutDTO> ssoLogin(UserLoginInpDTO userLoginInpDTO) {
      // 1.验证参数
      String mobile = userLoginInpDTO.getMobile();
      if (StringUtils.isEmpty(mobile)) {
         return setResultError("手机号码不能为空!");
      }
      String password = userLoginInpDTO.getPassword();
      if (StringUtils.isEmpty(password)) {
         return setResultError("密码不能为空!");
      }
      // 判断登陆类型
      String loginType = userLoginInpDTO.getLoginType();
      if (StringUtils.isEmpty(loginType)) {
         return setResultError("登陆类型不能为空!");
      }
      // 目的是限制范围
      if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID) || loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
              || loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC))) {
         return setResultError("登陆类型出现错误!");
      }

      // 设备信息
      String deviceInfor = userLoginInpDTO.getDeviceInfor();
      if (StringUtils.isEmpty(deviceInfor)) {
         return setResultError("设备信息不能为空!");
      }
      // 2.对登陆密码实现加密
      String newPassWord = MD5Util.MD5(password);
      // 3.使用手机号码+密码查询数据库 ，判断用户是否存在
      UserEntity userDo = userDao.login(mobile, newPassWord);
      if (userDo == null) {
         return setResultError("用户名称或者密码错误!");
      }
      return setResultSuccess(MiteBeanUtils.E2T(userDo, UserOutDTO.class));
   }

}

