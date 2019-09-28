package com.shop.sso.server.feign;

import com.mayikt.api.weixin.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-member")
public interface SsoLoginService extends MemberService {
}
