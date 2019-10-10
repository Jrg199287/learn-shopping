package com.mayikt.pay.web;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@EnableEurekaClient
//@EnableApolloConfig
@EnableFeignClients(basePackages="com.mayikt")  //开启FeignClient支持
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
@ComponentScan(basePackages={"com.mayikt"})//扫描接口
public class PayWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayWebApplication.class, args);
	}

}
