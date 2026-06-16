package com.kh.semi.auth.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.semi.auth.model.dto.LoginRequestDto;
import com.kh.semi.auth.model.dto.LoginResponse;
import com.kh.semi.auth.model.service.AuthService;
import com.kh.semi.common.api.ApiResponse;
import com.kh.semi.token.model.service.TokenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/auth")
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
	private final TokenService tokenService;
	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto lrd){
		LoginResponse res = authService.login(lrd)	;	
		return ResponseEntity.ok(res);
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<ApiResponse<Map<String, String>>> refresh(@RequestBody Map<String, String> refreshToken){
		return ResponseEntity.status(201).body(ApiResponse.created(tokenService.tokenLocation(refreshToken.get("refreshToken"))));
	}
	
	@GetMapping("logout")
	public ResponseEntity<ApiResponse<Map<String, String>>> logout(@RequestParam("id") String memberId){
		tokenService.logout(memberId);
		return ResponseEntity.status(200).body(ApiResponse.success("로그아웃", null));
	}
}
