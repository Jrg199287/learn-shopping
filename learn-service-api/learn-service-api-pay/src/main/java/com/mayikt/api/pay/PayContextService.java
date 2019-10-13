package com.mayikt.api.pay;

import com.unity.core.base.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
public interface PayContextService {
    @GetMapping("/toPayHtml")
    public BaseResponse<JSONObject> toPayHtml(@RequestParam("channelId") String channelId, @RequestParam("payToken") String payToken);

}
