package com.kh.semi.board.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.semi.board.model.dao.BoardMapper;
import com.kh.semi.board.model.dto.BoardDto;
import com.kh.semi.board.model.vo.Board;
import com.kh.semi.exception.CustomAuthenticationException;
import com.kh.semi.exception.FailSaveException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
	private final BoardMapper boardMapper;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public void save(BoardDto board) {
		validateBoard(board);

		// String animal = getAnimalTrait(board);

		Board boardEntity = Board.builder().boardTitle(board.getBoardTitle()).boardContent(board.getBoardContent())
				.boardCount(1l).userId(board.getUserId()).userPwd(passwordEncoder.encode(board.getUserPwd()))
				.categoryNo(board.getCategoryNo()).build();

		// validation 추가

		boardMapper.save(boardEntity);
	}

	private void validateBoard(BoardDto board) {
		if (board.getBoardTitle() == null || board.getBoardTitle().isEmpty()) {
			throw new FailSaveException("제목은 필수입니다.");
		}
	}
	/*
	// 동물 난수값을 바탕으로 동물 이름 생성 *미사용 코드 앞단 js로 빼야함
	private String getAnimalTrait(BoardDto board) {
		final String[] traitFst = { "정신나간", "용감한", "무서운", "행복한", "건방진", "재빠른", "게으른", "신난", "시건방진", "괘씸한" };
		final String[] traitSec = { "원숭이", "강아지", "황소", "개구리", "래트", "물범", "햄스터", "듀공", "돌고래", "기린" };
		// 1. LocalDateTime에서 밀리초(0~999) 추출
		long millis = board.getRegDate().getNano() / 1_000_000;
		// 2. 0~999를 0~9 범위로 변환
		int fstIndex = (int) (millis / 100) % 10; // 앞자리 (0~9)
		int secIndex = (int) (millis % 10); // 뒷자리 (0~9)

		return traitFst[fstIndex] + " " + traitSec[secIndex];
	} */

	public List<BoardDto> findAll(int page) {
		RowBounds rb = new RowBounds(page * 10, 10);
		return boardMapper.findAll(rb);
	}
	
	@Transactional
	public BoardDto findByNo(Long boardNo) {
		increaseCount(boardNo);

		return getBoardNoOrThrow(boardNo);
	}

	private void increaseCount(Long boardNo) {
		boardMapper.increaseCount(boardNo);
	}

	private BoardDto getBoardNoOrThrow(Long boardNo) {
		BoardDto boardDetail = boardMapper.findByNo(boardNo);

		if (boardDetail == null) {
			throw new FailSaveException("유효하지 않은 접근입니다.");
		}
		return boardDetail;
	}

	@Transactional
	public void deleteByNo(BoardDto board, Long boardNo) {
		// 비회원 검증 보드넘으로 아이디 뽑고, 현재 vo에서 확인
		UnitedValidate(board, boardNo);
		boardMapper.deleteByNo(boardNo);
	}

	@Transactional
	public void editByNo(@Valid BoardDto board, Long boardNo) {
		// 비회원 검증 보드넘으로 아이디 뽑고, 현재 vo에서 확인
		UnitedValidate(board, boardNo);
		boardMapper.editByNo(board, boardNo);
	}
	
	// ----- 공통 유효성 검사 코드 ------

	public void UnitedValidate(@Valid BoardDto board, Long boardNo) {
		// 비회원 검증 보드넘으로 아이디 뽑고, 현재 vo에서 확인
		validateUser(board.getUserId());
		validatePwd(board);
	}
	
	// ------ 유저 ID 검증 ----
	private void validateUser(String userId) {
		Board board = boardMapper.findById(userId);
		if (board == null || !board.getUserId().equals(userId)) {
			throw new CustomAuthenticationException("아이디가 일치하지 않습니다.");
		}
	}
	// ------ 유저 Pwd 검증 ----
	private void validatePwd(BoardDto inputBoard) {
		Board savedPwd = boardMapper.checkPwd(inputBoard);
		if (!passwordEncoder.matches(inputBoard.getUserPwd(), savedPwd.getUserPwd())) {
			throw new CustomAuthenticationException("비밀번호가 일치하지 않습니다.");
		}
	}

}
