package com.kh.semi.token.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RefreshToken {
	private String adminId;
	private String token;
	private Long expiration;
	
}
