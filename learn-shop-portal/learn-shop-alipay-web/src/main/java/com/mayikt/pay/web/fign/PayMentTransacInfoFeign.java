package com.mayikt.pay.web.fign;

import com.mayikt.api.pay.PayMentTransacInfoService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-pay")
public interface PayMentTransacInfoFeign extends PayMentTransacInfoService {
}
