package com.mayikt.member.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xxm
 * @create 2019-05-29 19:19
 */
//@EnableSwagger2Doc
@SpringBootApplication
@EnableEurekaClient
//@EnableApolloConfig
@EnableFeignClients(basePackages = "com.mayikt.member")  //开启FeignClient支持
@ComponentScan(basePackages={"com.mayikt.api","com.mayikt.member","com.mayikt.common"})//扫描接口
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class,args);
    }
}