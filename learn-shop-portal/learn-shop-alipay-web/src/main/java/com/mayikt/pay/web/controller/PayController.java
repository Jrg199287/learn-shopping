package com.mayikt.pay.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.pay.PayContextService;
import com.mayikt.api.pay.PayMentTransacInfoService;
import com.mayikt.api.pay.PaymentChannelService;
import com.mayikt.pay.web.fign.PayContextFeign;
import com.mayikt.pay.web.fign.PayMentTransacInfoFeign;
import com.mayikt.pay.web.fign.PaymentChannelFeign;
import com.unity.core.base.BaseResponse;
import com.unity.core.base.BaseWebController;
import learn.pay.dto.PayMentTransacDTO;
import learn.pay.dto.PaymentChannelDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 */
@Controller
public class PayController extends BaseWebController {

	private static final String ERROR_500_FTL ="500.ftl";
	@Autowired
	private PayMentTransacInfoFeign payMentTransacInfoFeign;
	@Autowired
	private PaymentChannelFeign paymentChannelFeign;
	@Autowired
	private PayContextFeign payContextFeign;


	@RequestMapping("/com/mayikt/pay/web")
	public String index(String payToken, Model model) {
		// 1.验证payToken参数
		if (StringUtils.isEmpty(payToken)) {
			setErrorMsg(model, "支付令牌不能为空!");
			return ERROR_500_FTL;
		}
		// 2.使用payToken查询支付信息
		BaseResponse<PayMentTransacDTO> tokenByPayMentTransac = payMentTransacInfoFeign.tokenByPayMentTransac(payToken);
		if (!isSuccess(tokenByPayMentTransac)) {
			setErrorMsg(model, tokenByPayMentTransac.getMsg());
			return ERROR_500_FTL;
		}
		// 3.查询支付信息
		PayMentTransacDTO data = tokenByPayMentTransac.getData();
		model.addAttribute("data", data);
		model.addAttribute("payToken", payToken);
		// 4.查询渠道信息
		List<PaymentChannelDTO> paymentChanneList = paymentChannelFeign.selectAll();
		model.addAttribute("paymentChanneList", paymentChanneList);
		return "index";
	}


	/**
	 *
	 * @param payToken
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/toPayHtml")
	public void payHtml(String channelId, String payToken, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		BaseResponse<JSONObject> payHtmlData = payContextFeign.toPayHtml(channelId, payToken);
		if (isSuccess(payHtmlData)) {
			JSONObject data = payHtmlData.getData();
			String payHtml = data.getString("payHtml");
			response.getWriter().print(payHtml);
		}

	}
}
