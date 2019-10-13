package com.mayikt.api.pay;

import learn.pay.dto.PaymentChannelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface PaymentChannelService {
    /**
     * 查询所有支付渠道
     *
     * @return
     */
    @GetMapping("/selectAll")
    public List<PaymentChannelDTO> selectAll();
}
