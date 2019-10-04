package com.mayikt.impl.goods.service;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2Doc
//@EnableApolloConfig
@EnableFeignClients(basePackages="com.mayikt.api")  //开启FeignClient支持
@ComponentScan(basePackages={"com.mayikt"})//扫描接口
@EnableElasticsearchRepositories(basePackages = { "com.mayikt.impl.goods" })
public class GoodsimplApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsimplApplication.class, args);
    }

}
