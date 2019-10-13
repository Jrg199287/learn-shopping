package com.mayikt.zuul.gateway.builder.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.author.service.AuthorizationService;
import com.mayikt.zuul.gateway.builder.GetWayBuild;
import com.mayikt.zuul.gateway.mapper.BlacklistMapper;
import com.mayikt.zuul.gateway.mapper.entity.MeiteBlackList;
import com.netflix.zuul.context.RequestContext;
import com.unity.core.base.BaseResponse;
import com.unity.core.constants.Constants;
import com.unity.core.core.utils.sign.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName : VerificationBuild  //类名
 * @Description :   //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-10-11 20:30  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
@Slf4j
@Component
public class VerificationBuild implements GetWayBuild {

    @Autowired BlacklistMapper blacklistMapper;
    @Autowired
    AuthorizationService authorizationService;

    /**
     * 白名单的实现方式
     * @param req
     * @param ipAddres
     * @param response
     * @return
     */
    public Boolean blackBlog(RequestContext req, String ipAddres, HttpServletResponse response) {
        // 2.查询数据库黑名单
        MeiteBlackList meiteBlacklist = blacklistMapper.findBlacklist(ipAddres);
        if (meiteBlacklist != null) {
            resultError(req, "ip:" + ipAddres + ",Insufficient access rights");
            return false;
        }
        log.info(">>>>>>ip:{},验证通过>>>>>>>", ipAddres);
        // 3.将ip地址传递到转发服务中
        response.addHeader("ipAddres", ipAddres);
        log.info(">>>>>>ip:{},验证通过>>>>>>>", ipAddres);
        return true;

    }

    /**
     * 验证参数的实现方式
     * @param req
     * @param ipAddres
     * @param request
     * @return
     */
    public Boolean toVerifyMap(RequestContext req, String ipAddres, HttpServletRequest request) {
        // 4.外网传递参数验证
        Map<String, String> verifyMap = SignUtil.toVerifyMap(request.getParameterMap(), false);
        if (!SignUtil.verify(verifyMap)) {
            resultError(req, "ip:" + ipAddres + ",Sign fail");
            return false;
        }
        return true;

    }

    @Override
    public Boolean apiAuthority(RequestContext ctx, HttpServletRequest request) {
        String servletPath = request.getServletPath();
        log.info(">>>>>servletPath:" + servletPath + ",servletPath.substring(0, 5):" + servletPath.substring(0, 5));
        if (!servletPath.substring(0, 7).equals("/public")) {
            return true;
        }
        String accessToken = request.getParameter("accessToken");
        log.info(">>>>>accessToken验证:" + accessToken);
        if (StringUtils.isEmpty(accessToken)) {
            resultError(ctx, "AccessToken cannot be empty");
            return false;
        }
        // 调用接口验证accessToken是否失效
        BaseResponse<JSONObject> appInfo = authorizationService.getAppInfo(accessToken);
        log.info(">>>>>>data:" + appInfo.toString());
        if (!isSuccess(appInfo)) {
            resultError(ctx, appInfo.getMsg());
            return false;
        }
        return true;
    }

    // 接口直接返回true 或者false
    public Boolean isSuccess(BaseResponse<?> baseResp) {
        if (baseResp == null) {
            return false;
        }
        if (!baseResp.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            return false;
        }
        return true;
    }

    /**
     * 网管返回结果
     * @param ctx
     * @param errorMsg
     */
    private void resultError(RequestContext ctx, String errorMsg) {
        ctx.setResponseStatusCode(401);
        ctx.setSendZuulResponse(false);
        ctx.setResponseBody(errorMsg);

    }

}
