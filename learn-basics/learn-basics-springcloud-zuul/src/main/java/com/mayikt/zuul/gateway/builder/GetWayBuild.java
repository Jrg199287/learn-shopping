package com.mayikt.zuul.gateway.builder;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName : GetWayBuild  //类名
 * @Description :   //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-10-11 20:25  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
public interface GetWayBuild {
    /**
     * 白名单验证
     * @param req
     * @param ipAddres
     * @param response
     * @return
     */
    Boolean blackBlog(RequestContext req, String ipAddres, HttpServletResponse response);

    /**
     * 参数验证
     * @param req
     * @param ipAddres
     * @param request
     * @return
     */
    Boolean toVerifyMap(RequestContext req, String ipAddres, HttpServletRequest request);
}
