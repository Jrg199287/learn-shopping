package com.mayikt.impl.pay.service;


import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableSwagger2Doc
@EnableEurekaClient
//@EnableApolloConfig
@MapperScan(basePackages = {"com.mayikt.impl.pay"})
@EnableFeignClients(basePackages = "com.mayikt.api")  //开启FeignClient支持
@ComponentScan(basePackages={"com.unity.core","com.mayikt","com.mayikt.impl.pay"})//扫描接口
public class PayImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayImplApplication.class, args);
    }

}
