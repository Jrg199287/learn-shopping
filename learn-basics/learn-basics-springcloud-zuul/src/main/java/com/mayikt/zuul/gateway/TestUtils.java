package com.mayikt.zuul.gateway;

import com.unity.core.core.utils.HttpClientUtils;
import com.unity.core.core.utils.sign.SignUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : TestUtils  //类名
 * @Description :   //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-10-11 21:10  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
public class TestUtils {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("payAmount","3200");
        map.put("orderId","20180302101457");
        map.put("userId","2");
        String resultstr = HttpClientUtils.sendHttpPostRequest("http://127.0.0.1/api-pay/cratePayToken", SignUtil.sign(map));
        System.out.println(resultstr.toString());
    }
}
