package com.mayikt.api.goods;

import com.unity.core.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import learn.member.dto.output.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Api(tags = "Elasticsearch查询接口")
public interface GoodsProductsInfo {
    /**
     *
     * @param goodId
     * @return
     */
    @GetMapping("/goods/search")
    @ApiOperation(value = "Elasticsearch查询信息接口")
    public BaseResponse<List<ProductDto>> queryGoods(@RequestParam String goodId);
}
