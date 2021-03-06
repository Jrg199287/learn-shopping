package com.mayikt.impl.member.service;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
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
@EnableFeignClients(basePackages = "com.mayikt.impl.member")    //开启FeignClient支持
@ComponentScan(basePackages = {"com.unity.core", "com.mayikt.impl"})//扫描接口
//@MapperScan(basePackages = "com.mayikt.impl.member.servi.dao")
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }
}
