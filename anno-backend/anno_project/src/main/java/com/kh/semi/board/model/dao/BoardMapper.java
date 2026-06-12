package com.kh.semi.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.kh.semi.board.model.dto.BoardDto;
import com.kh.semi.board.model.vo.Board;

import jakarta.validation.Valid;

@Mapper
public interface BoardMapper {

	@Insert("")
	void save(Board boardEntity);
	
	@Select("")
	List<BoardDto> findAll(RowBounds rb);

	@Select("")
	BoardDto findByNo(Long boardNo);
	
	@Insert("")
	void editByNo(@Valid BoardDto board, Long boardNo);

	void deleteByNo(Long boardNo);

	@Select("")
	Board findById(String userId);

	@Select("")
	Board checkPwd(BoardDto inputBoard);

	@Update("UPDATE ANNO_BOARD SET BOARD_COUNT = BOARD_COUNT + 1 WHERE BOARD_NO = #{boardNo}")
	void increaseCount(Long boardNo);
	
	// BOARD_TITLE, BOARD_CONTENT, USER_ID, USER_PWD, CATEGORY_NO 게시판 작성
	// BOARD_NO, BOARD_TITLE, BOARD_COUNT, 랜덤한 아이디값, REG_DATE, DEL_YN 게시판 목록 조회
	// BOARD_NO, BOARD_TITLE, BOARD_COUNT, 랜덤한 아이디값, REG_DATE, BOARD_CONTENT, DEL_YN 게시판 상세조회
	// BOARD_NO, USER_ID, USER_PWD, BOARD_TITLE, BOARD_CONTENT 게시판 수정
	// BOARD_NO, USER_ID, USER_PWD 게시판 삭제

}
