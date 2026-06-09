package com.kh.tv.member.model.service;

import org.springframework.stereotype.Service;

import com.kh.tv.member.model.dao.MemberMapper;
import com.kh.tv.member.model.dto.MemberDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberMapper memberMapper;

	public void signUp(@Valid MemberDto member) {
		
		if(CountById(member.getMemberId()) > 0) {
			
		}
	}
	
	private int CountById(String memberId) {
		return memberMapper.CountById(memberId);
	}

}
