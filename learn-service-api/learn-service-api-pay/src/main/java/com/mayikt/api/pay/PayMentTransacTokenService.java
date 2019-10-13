package com.mayikt.api.pay;

import com.alibaba.fastjson.JSONObject;
import com.unity.core.base.BaseResponse;
import learn.pay.dto.PayCratePayTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @version V1.0
 * @description: 支付交易服务接口
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 * 私自分享视频和源码属于违法行为。
 */
public interface PayMentTransacTokenService {

    /**
     * 创建支付令牌
     *
     * @return
     */
    @PostMapping("/cratePayToken")
    public BaseResponse<JSONObject> cratePayToken(@Validated PayCratePayTokenDto payCratePayTokenDto);

}
