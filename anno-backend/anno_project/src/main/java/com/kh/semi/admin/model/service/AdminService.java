package com.kh.semi.admin.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.semi.admin.model.dao.AdminMapper;
import com.kh.semi.admin.model.dto.AdminDto;
import com.kh.semi.admin.model.vo.Admin;
import com.kh.semi.exception.DuplicateMemberException;
import com.kh.semi.exception.FailSignUpException;
import com.kh.semi.token.model.dao.TokenMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {
	private final AdminMapper adminMapper;
	private final PasswordEncoder passwordEncoder;
	private final TokenMapper tokenMapper;
	
	@Transactional
	public void adminSignUp(AdminDto admin) {
		
		int count = adminMapper.countByMemberId(admin.getAdminId());
		
		if(count > 0) {
			throw new DuplicateMemberException("이미 존재하는 아이디입니다.");
		}
		
		Admin adminEntity = Admin.builder()
				.adminId(admin.getAdminId())
				.adminPwd(passwordEncoder.encode(admin.getAdminPwd())).build();
				
		int result = adminMapper.adminSignUp(adminEntity);
		
		if(1 > result) {
			throw new FailSignUpException("가입에 실패했습니다. 이미 존재하는 아이디입니다.");
		}
		
	}

}
