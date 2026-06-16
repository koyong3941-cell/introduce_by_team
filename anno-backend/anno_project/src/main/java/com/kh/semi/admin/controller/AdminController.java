package com.kh.semi.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.semi.admin.model.dto.AdminDto;
import com.kh.semi.admin.model.service.AdminService;
import com.kh.semi.board.model.dto.BoardDto;
import com.kh.semi.common.api.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admins")
public class AdminController {
	private final AdminService adminService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> amdinSignUp(@RequestBody @Valid AdminDto admin){
		adminService.adminSignUp(admin);
			
		return ResponseEntity.status(201).body(ApiResponse.success(null));
	}
	
	// admin 컨트롤 단
		@GetMapping("/boards")
		public ResponseEntity<ApiResponse<List<BoardDto>>> findAllByAdmin(@RequestParam(value = "page", defaultValue ="0") int page
				,Authentication auth){
			List<BoardDto> boardLists = adminService.findAllByAdmin(page);
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@boardDto : {} ", boardLists);
		    return ResponseEntity.ok(ApiResponse.success(boardLists));
		}
		
		@DeleteMapping("/boards/{boardNo}")
		public ResponseEntity<Void> deleteByAdmin(@RequestBody BoardDto board, @PathVariable(name = "boardNo") Long boardNo) {
			adminService.deleteByAdmin(board, boardNo);
			
			return ResponseEntity.ok().build();
		}
		
		@PatchMapping("/boards/{boardNo}")
		public ResponseEntity<Void> editByAdmin(@RequestBody @Valid BoardDto board,@PathVariable(name = "boardNo")Long boardNo) {
			adminService.editByAdmin(board, boardNo);
			
			return ResponseEntity.noContent().build();
		}
	
}
