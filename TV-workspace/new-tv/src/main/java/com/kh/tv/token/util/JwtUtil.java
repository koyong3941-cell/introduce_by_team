package com.kh.tv.token.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {
	// 토큰을 만드는 기능, 토큰을 검증하는 기능
	
	// header, payload, signature
	
	@Value(value = "${jwt.secret}")
	private String secretKey;
	private SecretKey key;
	  
	@PostConstruct
	public void init() {
		// log.info("엥? : {}", secretKey);
		byte[] arr = Base64.getDecoder().decode(secretKey);
		this.key = Keys.hmacShaKeyFor(arr);
	}

	/* Duration.ofMinutes(15) 만료기간은 15분
	public String getAccessToken(CustomUserDetails user) {
		return Jwts.builder()
				   .subject(user.getUsername())
				   .issuedAt(new Date())
				   .expiration(Date.from(Instant.now().plus(Duration.ofMinutes(540))))
				   .claim("memberName", user.getMemberName())
				   .signWith(key)
				   .compact();
					
	}

	public String getRefreshToken(CustomUserDetails user) {
		return Jwts.builder()
				   .subject(user.getUsername())
				   .issuedAt(new Date())
				   .expiration(Date.from(Instant.now().plus(Duration.ofDays(5))))
				   .claim("memberName", user.getMemberName())
				   .signWith(key)
				   .compact();
	}
	
	public Claims parseJwt(String token) {
		return Jwts.parser()
				   .verifyWith(key)
				   .build()
				   .parseSignedClaims(token)
				   .getPayload();
	} */
	
} 
