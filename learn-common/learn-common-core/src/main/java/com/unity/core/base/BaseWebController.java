package com.unity.core.base;

import com.unity.core.constants.Constants;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.UserAgent;
import nl.bitwalker.useragentutils.Version;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public class BaseWebController {
	/**
	 * 500页面
	 */
	// 接口直接返回true 或者false
	public Boolean isSuccess(BaseResponse<?> baseResp) {
		if (baseResp == null) {
			return false;
		}
		if (baseResp.getCode().equals(Constants.HTTP_RES_CODE_500)) {
			return false;
		}
		return true;
	}

	/**
	 * 获取浏览器信息
	 * 
	 * @return
	 */
	public String webBrowserInfo(HttpServletRequest request) {
		// 获取浏览器信息
		String aa = request.getHeader("User-Agent");
		UserAgent bb =  UserAgent.parseUserAgentString(aa);
		Browser browser = bb.getBrowser();
		// 获取浏览器版本号
		Version version = browser.getVersion(request.getHeader("User-Agent"));
		String info = browser.getName() + "/" + version.getVersion();
		return info;
	}

	public void setErrorMsg(Model model, String errorMsg) {
		model.addAttribute("error", errorMsg);
	}

}
