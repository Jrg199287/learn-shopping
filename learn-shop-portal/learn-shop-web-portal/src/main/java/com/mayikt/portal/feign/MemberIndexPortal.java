package com.mayikt.portal.feign;

import com.mayikt.api.weixin.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-member")
public interface MemberIndexPortal extends MemberService {
}
