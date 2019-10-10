package com.mayikt.pay.web.fign;

import com.mayikt.api.pay.PayContextService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-pay")
public interface PayContextFeign extends PayContextService {
}
