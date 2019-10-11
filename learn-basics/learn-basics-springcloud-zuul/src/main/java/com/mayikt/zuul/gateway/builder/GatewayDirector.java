package com.mayikt.zuul.gateway.builder;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 建造者设计模式
 */
@Component
public class GatewayDirector {
	@Resource(name = "verificationBuild")
	private GetWayBuild gatewayBuild;
	public void direcot(RequestContext ctx, String ipAddres, HttpServletResponse response, HttpServletRequest request) {
		/**
		 * 黑名单拦截
		 */
		Boolean blackBlock = gatewayBuild.blackBlog(ctx, ipAddres, response);
		if (!blackBlock) {
			return;
		}
		/**
		 * 参数验证
		 */
		Boolean verifyMap = gatewayBuild.toVerifyMap(ctx, ipAddres, response);
		if (!verifyMap) {
			return;
		}
	}

}
