package com.kh.semi.auth.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.semi.admin.model.dto.AdminDto;

@Mapper
public interface AuthMapper {

	@Select("SELECT ADMIN_ID, ADMIN_PWD, ROLE, DEL_YN FROM ANNO_ADMIN WHERE DEL_YN ='N' AND ADMIN_ID = #{username}")
	AdminDto loadUser(String username);
	
}
