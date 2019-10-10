package com.mayikt.pay.web.fign;

import com.mayikt.api.pay.PaymentChannelService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-pay")
public interface PaymentChannelFeign extends PaymentChannelService {
}
