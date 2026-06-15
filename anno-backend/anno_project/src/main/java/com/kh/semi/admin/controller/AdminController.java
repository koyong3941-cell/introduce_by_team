package com.kh.semi.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.semi.admin.model.dto.AdminDto;
import com.kh.semi.admin.model.service.AdminService;
import com.kh.semi.auth.model.vo.CustomUserDetails;
import com.kh.semi.common.api.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
	private final AdminService adminService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> amdinSignUp(@RequestBody @Valid AdminDto admin){
		
		adminService.adminSignUp(admin);
			
		return ResponseEntity.status(201).body(ApiResponse.success(null));
	}
	



	
}
