package com.mayikt.impl.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.weixin.MemberLoginService;
import com.mayikt.impl.member.service.dao.UserMapper;
import com.mayikt.impl.member.service.dao.UserTokenDo;
import com.mayikt.impl.member.service.dao.UserTokenMapper;
import com.unity.core.base.BaseApiService;
import com.unity.core.base.BaseResponse;
import com.unity.core.constants.Constants;
import com.unity.core.core.transaction.RedisDataSoureceTransaction;
import com.unity.core.core.utils.GenerateToken;
import com.unity.core.core.utils.MD5Util;
import com.unity.core.core.utils.RedisUtil;
import com.unity.core.core.utils.TypeCastHelper;
import learn.member.dto.input.UserInpDTO;
import learn.member.dto.input.UserLoginInpDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : MemberLoginServiceImpl  //类名
 * @Description : 会员登陆实现  //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-09-18 20:41  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
@RestController
public class MemberLoginServiceImpl extends BaseApiService<JSONObject> implements MemberLoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;
    /**
     * 手动事务工具类
     */
    @Autowired
    private RedisDataSoureceTransaction manualTransaction;

    @Autowired
    private GenerateToken generateToken;

    /**
     * redis 工具类
     */
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO) {
        // 1.验证参数
        String mobile = userLoginInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String password = userLoginInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }
        String loginType = userLoginInpDTO.getLoginType();
        if (StringUtils.isEmpty(loginType)) {
            return setResultError("登陆类型不能为空!");
        }
        if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID) || loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
                || loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC))) {
            return setResultError("登陆类型出现错误!");
        }

        // 设备信息
        String deviceInfor = userLoginInpDTO.getDeviceInfor();
        if (StringUtils.isEmpty(deviceInfor)) {
            return setResultError("设备信息不能为空!");
        }
        String newPassWord = MD5Util.MD5(password);
        // 2.用户名称与密码登陆
        UserInpDTO userDo = userMapper.login(mobile, newPassWord);
        if (userDo == null) {
            return setResultError("用户名称与密码错误!");
        }
        TransactionStatus transactionStatus = null;
        // 3.查询之前是否有过登陆
        try {

            // 1.获取用户UserId
            Long userId = userDo.getUserId();
            // 2.生成用户令牌Key
            String keyPrefix = Constants.MEMBER_TOKEN_KEYPREFIX + loginType;
            // 5.根据userId+loginType 查询当前登陆类型账号之前是否有登陆过，如果登陆过 清除之前redistoken
            UserTokenDo userTokenDo = userTokenMapper.selectByUserIdAndLoginType(userId, loginType);
            transactionStatus = manualTransaction.begin();
            // // ####开启手动事务
            if (userTokenDo != null) {
                // 如果登陆过 清除之前redistoken
                String oriToken = userTokenDo.getToken();
                // 移除Token
                generateToken.removeToken(oriToken);
                int updateTokenAvailability = userTokenMapper.updateTokenAvailability(userId, loginType);
                if (updateTokenAvailability < 0) {
                    manualTransaction.rollback(transactionStatus);
                    return setResultError("系统错误");
                }
            }

            // 4.将用户生成的令牌插入到Token记录表中
            UserTokenDo userToken = new UserTokenDo();
            userToken.setUserId(userId);
            userToken.setLoginType(userLoginInpDTO.getLoginType());
            String newToken = generateToken.createToken(keyPrefix, userId + "");
            userToken.setToken(newToken);
            userToken.setDeviceInfor(deviceInfor);
            int result = userTokenMapper.insertUserToken(userToken);
            if (!toDaoResult(result)) {
                manualTransaction.rollback(transactionStatus);
                return setResultError("系统错误!");
            }

            // #######提交事务
            JSONObject data = new JSONObject();
            data.put("token", newToken);
            manualTransaction.commit(transactionStatus);
            return setResultSuccess(data);
        } catch (Exception e) {
            try {
                // 回滚事务
                manualTransaction.rollback(transactionStatus);
            } catch (Exception e1) {
            }
            return setResultError("系统错误!");
        }

    }

    @Override
    public BaseResponse<JSONObject> delToken(String token) {
        return null;
    }

}

