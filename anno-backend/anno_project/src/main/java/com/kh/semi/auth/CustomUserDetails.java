package com.kh.semi.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomUserDetails implements UserDetails{
	private String username;
	private String password;
	private String memberName;
	private Collection<? extends GrantedAuthority> authorities;
	private String status;
	
	
	/*
	 * 스프링 시큐리티 보안 로그인 설계 순서 (클래스 중심)
User (Entity/VO)

로그인 정보를 담는 DB 테이블과 매핑되는 핵심 클래스야.

UserDetails (Interface 구현체)

시큐리티가 이해할 수 있는 "사용자 인증 객체"로 만드는 클래스야. User를 감싸서 권한, 계정 만료 여부 등을 시큐리티한테 알려줘.

UserDetailsService (Interface 구현체)

로그인의 심장! DB에서 username을 조회해서 UserDetails 객체를 반환하는 클래스야. loadUserByUsername 메서드를 여기서 구현해.

PasswordEncoder (Bean 설정)

비밀번호를 안전하게 암호화하고, 검증할 때 사용하는 툴이야. (보통 BCryptPasswordEncoder 사용)

SecurityConfig (설정 클래스)

가장 중요해! 어떤 URL은 로그인해야 들어갈 수 있고, 어떤 URL은 그냥 들어갈 수 있는지 설정해. 또 UserDetailsService랑 PasswordEncoder를 여기서 등록(Bean)해.

AuthenticationSuccessHandler / FailureHandler (선택 사항)

로그인 성공/실패 시 어떤 페이지로 보낼지, 어떤 로직을 수행할지 결정하는 클래스야.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}
