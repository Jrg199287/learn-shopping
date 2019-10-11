package com.mayikt.zuul.gateway.mapper;

import com.mayikt.zuul.gateway.mapper.entity.MeiteBlackList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BlacklistMapper {

	@Select(" select ID AS ID ,ADRESS AS ipAddres,restriction_type  as restrictionType, availability as availability from meite_blacklist where  ADRESS =#{ipAddres} and  restriction_type='1' ")
    MeiteBlackList findBlacklist(String ipAddres);

}
