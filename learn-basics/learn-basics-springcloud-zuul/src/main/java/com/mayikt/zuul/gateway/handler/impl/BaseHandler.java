package com.mayikt.zuul.gateway.handler.impl;

import com.mayikt.zuul.gateway.handler.GatewayHandler;
import com.netflix.zuul.context.RequestContext;

/**
 * BaseHandler
 * 
 * 
 * @description:
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
public class BaseHandler {
	public GatewayHandler nextGatewayHandler;

	public void setNextHandler(GatewayHandler nextGatewayHandler) {
		this.nextGatewayHandler = nextGatewayHandler;
	}

	public void resultError(Integer code, RequestContext ctx, String errorMsg) {
		ctx.setResponseStatusCode(code);
		// 网关响应为false 不会转发服务
		ctx.setSendZuulResponse(false);
		ctx.setResponseBody(errorMsg);
		ctx.getResponse().setContentType("text/html;charset=UTF-8");

	}

}
