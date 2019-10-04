package com.mayikt.portal.feign;

import com.mayikt.api.weixin.QQAuthoriService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-member")
public interface QqAuthorLoginFign extends QQAuthoriService {
}
