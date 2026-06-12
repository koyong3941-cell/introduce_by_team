package com.kh.semi.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.semi.board.model.dto.BoardDto;
import com.kh.semi.board.model.service.BoardService;
import com.kh.semi.common.api.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
@CrossOrigin("*")
public class BoardController {
	private final BoardService boardService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> save(@ModelAttribute @Valid BoardDto board){
		boardService.save(board);
		return ResponseEntity.status(201).body(ApiResponse.success(null));
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<BoardDto>>> findAll(@RequestParam(value = "page", defaultValue ="0") int page){
		List<BoardDto> boardLists = boardService.findAll(page);
		return ResponseEntity.ok(ApiResponse.success(boardLists));
	}
	
	@GetMapping("/{boardNo}")
	public ResponseEntity<ApiResponse<BoardDto>> findByNo(@PathVariable(name = "boardNo")Long BoardNo) {
		BoardDto board = boardService.findByNo(BoardNo);
		
		return ResponseEntity.ok(ApiResponse.success(board));
	}
	
	@DeleteMapping("/{boardNo}")
	public ResponseEntity<Void> deleteByNo(@RequestBody BoardDto board, @PathVariable(name = "boardNo") Long BoardNo) {
		boardService.deleteByNo(board, BoardNo);
		
		return ResponseEntity.ok().build();
	}
	
	@PatchMapping("/{boardNo}")
	public ResponseEntity<Void> editByNo(@RequestBody @Valid BoardDto board,@PathVariable(name = "boardNo")Long BoardNo) {
		boardService.editByNo(board, BoardNo);
		
		return ResponseEntity.noContent().build();
	
	}
		

}
