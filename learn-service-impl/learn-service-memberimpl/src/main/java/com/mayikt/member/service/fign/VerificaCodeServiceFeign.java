package com.mayikt.member.service.fign;

import com.mayikt.api.weixin.VerificaCodeService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-weixin")
public interface VerificaCodeServiceFeign extends VerificaCodeService {
}
