package com.mayikt.impl.member.service.dao;

import learn.member.dto.input.UserInpDTO;
import learn.member.dto.input.UserLoginInpDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
	@Insert("INSERT INTO `meite_user` VALUES (null,#{mobile}, #{email}, #{password}, #{userName}, null, null, null, '1', null, null, null);")
	int register(UserInpDTO userDo);

	@Select("SELECT * FROM meite_user WHERE MOBILE=#{mobile};")
	UserInpDTO existMobile(@Param("mobile") String mobile);

	@Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE,EMAIL AS EMAIL,PASSWORD AS PASSWORD, USER_NAME AS USER_NAME ,SEX AS SEX ,AGE AS AGE ,CREATE_TIME AS CREATETIME,IS_AVALIBLE AS ISAVALIBLE,PIC_IMG AS PICIMG,QQ_OPENID AS QQOPENID,WX_OPENID AS WXOPENID "
			+ "  FROM meite_user  WHERE MOBILE=#{mobile} and password=#{password};")
	UserInpDTO login(@Param("mobile") String mobile, @Param("password") String password);

	@Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE,EMAIL AS EMAIL,PASSWORD AS PASSWORD, USER_NAME AS USER_NAME ,SEX AS SEX ,AGE AS AGE ,CREATE_TIME AS CREATETIME,IS_AVALIBLE AS ISAVALIBLE,PIC_IMG AS PICIMG,QQ_OPENID AS QQOPENID,WX_OPENID AS WXOPENID"
			+ " FROM meite_user WHERE user_Id=#{userId}")
	UserInpDTO findByUserId(@Param("userId") Long userId);


}
