package com.mayikt.impl.weixin.service;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xxm
 * @create 2019-05-29 19:19
 */
@EnableSwagger2Doc
@SpringBootApplication
@EnableEurekaClient
@EnableApolloConfig
@EnableFeignClients(basePackages = "com.mayikt.impl.weixin")    //开启FeignClient支持
@ComponentScan(basePackages={"com.mayikt.impl.weixin","com.unity.core","com.mayikt.api"})//扫描接口
public class WeiXinApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeiXinApplication.class,args);
    }
}
