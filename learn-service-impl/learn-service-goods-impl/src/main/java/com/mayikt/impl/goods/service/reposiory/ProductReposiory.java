package com.mayikt.impl.goods.service.reposiory;

import com.mayikt.impl.goods.service.entity.ProductEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

public interface ProductReposiory extends ElasticsearchRepository<ProductEntity, Long> {

}
 