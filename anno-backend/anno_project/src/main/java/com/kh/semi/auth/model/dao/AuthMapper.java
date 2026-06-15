package com.kh.semi.auth.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.semi.admin.model.dto.AdminDto;

@Mapper
public interface AuthMapper {

	@Select("""
				SELECT
			
			""")
	AdminDto loadUser(String username);
	
}
