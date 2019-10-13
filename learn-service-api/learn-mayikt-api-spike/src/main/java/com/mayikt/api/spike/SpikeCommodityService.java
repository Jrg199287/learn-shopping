package com.mayikt.api.spike;

import com.alibaba.fastjson.JSONObject;
import com.unity.core.base.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 秒杀商品服务接口
 * 
 * 
 * @description:
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
public interface SpikeCommodityService {

	/**
	 * 用户秒杀接口 phone和userid都可以的
	 * 
	 * @phone 手机号码<br>
	 * @seckillId 库存id
	 * @return
	 */
	@RequestMapping("/spike")
	public BaseResponse<JSONObject> spike(String phone, Long seckillId);

	/**
	 * 新增对应商品库存令牌桶
	 * 
	 * @seckillId 商品库存id
	 */
	@RequestMapping("/addSpikeToken")
	public BaseResponse<JSONObject> addSpikeToken(Long seckillId, Long tokenQuantity);

}
