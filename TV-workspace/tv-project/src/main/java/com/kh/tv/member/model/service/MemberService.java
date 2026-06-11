package com.kh.tv.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.tv.exception.CountDuplicationException;
import com.kh.tv.exception.FailSignUpException;
import com.kh.tv.member.model.dao.MemberMapper;
import com.kh.tv.member.model.dto.MemberDto;
import com.kh.tv.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public void signUp(MemberDto member) {
		
		CountById(member.getMemberId());
		
		Member tvMember = Member.builder()
								.memberId(member.getMemberId())
								.memberPwd(passwordEncoder.encode(member.getMemberPwd()))
								.memberName(member.getMemberName())
								.role("ROLE_USER")
								.build();
		
		int result = memberMapper.SignUp(tvMember);
	
		if(result < 1) {
			throw new FailSignUpException("관리자를 찾아주세요.");
		}
		
		
	}
	
	
	private void CountById(String memberId) {
		 if(memberMapper.CountById(memberId) > 0) {
			 throw new CountDuplicationException("중복된 아이디입니다.");
		 }
	}

}
