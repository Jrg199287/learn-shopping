package com.mayikt.impl.spike.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.mayikt.impl.spike.service.mapper")
@EnableAsync
@EnableHystrix
@ComponentScan(basePackages={"com.unity.core","com.mayikt","com.mayikt.impl.spike"})//扫描接口
public class SpikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpikeApplication.class, args);
	}

}
