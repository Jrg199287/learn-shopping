package com.mayikt.zuul.gateway;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 微服务网关入口
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableSwagger2Doc
@EnableApolloConfig
@EnableFeignClients(basePackages = "com.mayikt.api")  //开启FeignClient支持
@MapperScan(value = "com.mayikt.zuul.gateway.mapper")
@ComponentScan(basePackages = "com.mayikt")
@EnableAsync
public class GateWayApplication {

	// 获取ApolloConfig
	@ApolloConfig
	private Config appConfig;


	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}

	// 添加文档来源  访问swagger-ui页面的时候  每次都会访问以下get方法
	@Component
	@Primary
	class DocumentationConfig implements SwaggerResourcesProvider {
		@Override
		public List<SwaggerResource> get() {
			// 网关使用服务别名获取远程服务的SwaggerApi
			return resources();
		}

		/**
		 * 从阿波罗服务器中获取resources
		 *
		 * @return
		 */
		private List<SwaggerResource> resources() {

			List resources = new ArrayList<>();
			// app-itmayiedu-order
			// 网关使用服务别名获取远程服务的SwaggerApi
			String swaggerDocJson = swaggerDocument();
			JSONArray jsonArray = JSONArray.parseArray(swaggerDocJson);
			for (Object object : jsonArray) {
				JSONObject jsonObject = (JSONObject) object;
				String name = jsonObject.getString("name");
				String location = jsonObject.getString("location");
				String version = jsonObject.getString("version");
				resources.add(swaggerResource(name, location, version));
			}
			return resources;
		}

		/**
		 * 获取swaggerDocument配置
		 *
		 * @return
		 */
		private String swaggerDocument() {
			String property = appConfig.getProperty("mayikt.zuul.swaggerDocument", "");
			return property;
		}



		private SwaggerResource swaggerResource(String name, String location, String version) {
			SwaggerResource swaggerResource = new SwaggerResource();
			swaggerResource.setName(name);
			swaggerResource.setLocation(location);
			swaggerResource.setSwaggerVersion(version);
			return swaggerResource;
		}

	}

}