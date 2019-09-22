package com.mayikt.impl.member.service.dao;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserTokenMapper {

	@Select("SELECT id as id ,token as token ,login_type as LoginType, device_infor as deviceInfor ,is_availability as isAvailability,user_id as userId"
			+ "" + ""
			+ "   FROM meite_user_token WHERE user_id=#{userId} AND login_type=#{loginType} and is_availability ='0'; ")
	UserTokenDo selectByUserIdAndLoginType(@Param("userId") Long userId, @Param("loginType") String loginType);

	@Update("    update meite_user_token set is_availability ='1',update_time=now()   where user_id=#{userId} and login_type =#{loginType} ")
	int updateTokenAvailability(@Param("userId") Long userId, @Param("loginType") String loginType);

	// INSERT INTO `meite_user_token` VALUES ('2', '1', 'PC', '苹果7p', '1', '1');

	@Insert("    INSERT INTO `meite_user_token` VALUES (null, #{token},#{loginType}, #{deviceInfor}, 0, #{userId} ); ")
	int insertUserToken(UserTokenDo userTokenDo);
}
