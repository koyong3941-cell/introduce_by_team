package com.kh.tv.member.model.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.tv.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	@Insert("INSERT INTO TV_MEMBER(MEMBER_ID, MEMBER_PWD, MEMBER_NAME, ROLE) VALUES (#{memberId}, #{memberPwd}, #{memberName}, #{role})")
	int signUp(Member member);
	
	@Select("SELECT COUNT(*) FROM TV_MEMBER WHERE MEMBER_ID = #{memberId}")
	int countById(String memberId);
}
