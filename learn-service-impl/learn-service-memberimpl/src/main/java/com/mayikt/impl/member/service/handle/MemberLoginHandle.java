package com.mayikt.impl.member.service.handle;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.impl.member.service.dao.UserDao;
import com.mayikt.impl.member.service.dao.UserTokenDao;
import com.mayikt.impl.member.service.entity.UserEntity;
import com.mayikt.impl.member.service.entity.UserToken;
import com.unity.core.aop.RedisTransAdvice;
import com.unity.core.base.BaseApiService;
import com.unity.core.base.CommonCode;
import com.unity.core.constants.Constants;
import com.unity.core.core.utils.GenerateToken;
import com.unity.core.core.utils.RediskeyUtils;
import com.unity.core.exception.ExceptionCast;
import learn.member.dto.input.UserLoginInpDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xxm
 * @create 2019-06-12 3:44
 */
@Component
public class MemberLoginHandle extends BaseApiService<JSONObject> {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserTokenDao userTokenDao;
    @Autowired
    private GenerateToken generateToken;


    @RedisTransAdvice
    public JSONObject doLogin(UserLoginInpDTO userLoginInpDTO, String loginType, String deviceInfor, UserEntity userDo) {
        // 3.查询之前是否有过登陆
        Long userId = userDo.getUserId();
        UserToken userTokenDo = userTokenDao.selectByUserIdAndLoginType(userId, loginType);
        // 开启手动事务
        if (userTokenDo != null) {
            // 4.清除之前的token
            String token = userTokenDo.getToken();
            generateToken.removeToken(token);
            int result = userTokenDao.updateTokenAvailability(token);
            if (!toDaoResult(result)) {
                ExceptionCast.cast(CommonCode.SERVER_ERROR);
            }
        }


        // 如果有传递openid参数，修改到数据中
        String qqOpenId = userLoginInpDTO.getQqOpenId();
        if (!StringUtils.isEmpty(qqOpenId)) {
            userDao.updateUserOpenId(qqOpenId, userId);
        }

        // 5. 生成新的token
        String token = generateToken.createToken(RediskeyUtils.getTokenPrefix(loginType), userId + "", Constants.MEMBRE_LOGIN_TOKEN_TIME);
        JSONObject tokenData = new JSONObject();
        tokenData.put("token", token);
        // 6.存入在数据库中
        UserToken userToken = new UserToken();
        userToken.setUserId(userId);
        userToken.setLoginType(userLoginInpDTO.getLoginType());
        userToken.setToken(token);
        userToken.setDeviceInfor(deviceInfor);
        Integer result = userTokenDao.insert(userToken);
        if (!toDaoResult(result)) {
            ExceptionCast.cast(CommonCode.SERVER_ERROR);
        }
        //int i = 1/0;  // 调试异常
        return tokenData;
    }

    /**
     * 删除redis中的登录状态,并将meite_user_token表中的  当前终端在线状态设置为  失效:is_availability = 0
     *
     * @param token
     * @return
     */
    @RedisTransAdvice
    public Boolean doDelKey(String token) {
        generateToken.removeToken(token);
        int result = userTokenDao.updateTokenAvailability(token);
        if (!toDaoResult(result)) {
            ExceptionCast.cast(CommonCode.SERVER_ERROR);
        }
        return true;
    }
}
