package com.kh.semi.admin.model.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.semi.admin.model.vo.Admin;

@Mapper
public interface AdminMapper {
	
	@Insert("INSERT INTO ANNO_ADMIN (ADMIN_ID, ADMIN_PWD, ROLE) VALUES (#{adminId}, #{adminPwd}, 'ROLE_ADMIN')")
	int adminSignUp(Admin admin);

	@Select("SELECT COUNT(*) FROM ANNO_ADMIN WHERE ADMIN_ID = #{adminId}")
	int countByMemberId(String adminId);
		
}
