package com.kh.semi.auth.model.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.semi.admin.model.dto.AdminDto;
import com.kh.semi.auth.model.dao.AuthMapper;
import com.kh.semi.auth.model.vo.CustomUserDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final AuthMapper authMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//log.info("과연?? > {}", username);
		// 여기선 우리가 무엇을 해야하는가?
		
		AdminDto admin = authMapper.loadUser(username);
		 
		log.info("조회된 정보 : {}", admin);
		
		if(admin == null) {
			throw new UsernameNotFoundException("요거 있다구요잇");
		}
		
		
		return CustomUserDetails.builder().username(admin.getAdminId())
											.password(admin.getAdminPwd())
											.authorities(Collections.singletonList(new SimpleGrantedAuthority(admin.getRole())))
											.status(admin.getStatus())
											.build();
	}

}
