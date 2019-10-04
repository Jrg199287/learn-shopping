package com.mayikt.impl.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.unity.core.base.BaseApiService;
import com.unity.core.base.BaseResponse;
import com.mayikt.api.weixin.MemberRegisterService;
import com.unity.core.constants.Constants;
import com.unity.core.core.utils.MD5Util;
import com.mayikt.impl.member.service.fign.VerificaCodeServiceFeign;
import com.mayikt.impl.member.service.dao.UserMapper;
import learn.member.dto.input.UserInpDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Service
public class MemberRegisterServiceImpl extends BaseApiService<JSONObject> implements MemberRegisterService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerificaCodeServiceFeign verificaCodeServiceFeign;

    @Override
    public BaseResponse<JSONObject> register(@RequestBody UserInpDTO userEntity, String registCode) {
        // 1.验证参数
        String mobile = userEntity.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String password = userEntity.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }
        String newPassWord = MD5Util.MD5(password);
        // 将密码采用MD5加密
        userEntity.setPassword(newPassWord);
        // 调用微信接口,验证注册码是否正确
        BaseResponse<JSONObject> resultVerificaWeixinCode = verificaCodeServiceFeign.verificaWeixinCode(mobile,
                registCode);
        if (!resultVerificaWeixinCode.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            return setResultError(resultVerificaWeixinCode.getMsg());
        }
        int registerResult = userMapper.register(userEntity);
        return registerResult > 0 ? setResultSuccess("注册成功") : setResultSuccess("注册失败");

    }

}
