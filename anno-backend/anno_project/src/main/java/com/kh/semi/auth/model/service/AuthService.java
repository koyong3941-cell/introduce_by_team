package com.kh.semi.auth.model.service;

import java.util.Date;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.kh.semi.auth.model.dto.LoginRequestDto;
import com.kh.semi.auth.model.dto.LoginResponse;
import com.kh.semi.auth.model.vo.CustomUserDetails;
import com.kh.semi.exception.CustomAuthenticationException;
import com.kh.semi.token.model.service.TokenService;

import io.jsonwebtoken.Jwts;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	public LoginResponse login(@Valid LoginRequestDto lrd) {
		Authentication auth = null;

		try {
			auth = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(lrd.getAdminId(), lrd.getAdminPwd()));
		} catch (AuthenticationException e) {
			throw new CustomAuthenticationException("아이디 또는 비밀번호가 이상합니다");
		}

		//인증 성공함
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		// 토큰 발급
		
			Map<String, String> tokens = tokenService.getTokens(user);
			return LoginResponse.builder().adminId(user.getUsername())
													.role(user.getAuthorities().iterator().next().getAuthority())
													.accessToken(tokens.get("accessToken"))
													.refreshToken(tokens.get("refreshToken"))
													.build();

	}

}

