package com.mayikt.api.pay;

import com.unity.core.base.BaseResponse;
import learn.pay.dto.PayMentTransacDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 */
public interface PayMentTransacInfoService {
    @GetMapping("/tokenByPayMentTransac")
    public BaseResponse<PayMentTransacDTO> tokenByPayMentTransac(@RequestParam("token") String token);
}
