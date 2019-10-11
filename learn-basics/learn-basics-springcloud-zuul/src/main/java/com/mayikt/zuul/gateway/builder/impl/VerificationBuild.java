package com.mayikt.zuul.gateway.builder.impl;

import com.mayikt.zuul.gateway.builder.GetWayBuild;
import com.mayikt.zuul.gateway.mapper.BlacklistMapper;
import com.mayikt.zuul.gateway.mapper.entity.MeiteBlackList;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName : VerificationBuild  //类名
 * @Description :   //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-10-11 20:30  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
@Slf4j
public class VerificationBuild implements GetWayBuild {

    @Autowired BlacklistMapper blacklistMapper;

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
     * @param ipAdress
     * @param respons
     * @return
     */
    public Boolean toVerifyMap(RequestContext req, String ipAdress, HttpServletResponse respons) {
        return null;
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
