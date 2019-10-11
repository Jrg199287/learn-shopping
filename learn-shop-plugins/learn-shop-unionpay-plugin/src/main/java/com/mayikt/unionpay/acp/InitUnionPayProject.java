package com.mayikt.unionpay.acp;

import com.mayikt.unionpay.acp.sdk.SDKConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 银联支付项目初始化
 */
@Component
public class InitUnionPayProject implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SDKConfig.getConfig().loadPropertiesFromSrc();
    }
}
