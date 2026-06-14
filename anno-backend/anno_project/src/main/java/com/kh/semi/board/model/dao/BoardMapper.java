package com.kh.semi.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.kh.semi.board.model.dto.BoardDto;
import com.kh.semi.board.model.dto.Category;
import com.kh.semi.board.model.vo.Board;

@Mapper
public interface BoardMapper {

	@Insert("""
				INSERT
				  INTO
				  		ANNO_BOARD	
				  		( 
				  	 BOARD_TITLE
				  	,BOARD_CONTENT
				  	,USER_ID
				  	,USER_PWD
				  	,CATEGORY_NO
				  		)
				VALUES
						(
					 #{boardTitle}
					,#{boardContent}
					,#{userId}
					,#{userPwd}
					,#{categoryNo}
						)
			""")
	void save(Board boardEntity);
	
	@Select("""
				SELECT
					BOARD_NO 
					,BOARD_TITLE 
					,BOARD_CONTENT 
					,BOARD_COUNT 
					,CATEGORY_NO
					,REG_DATE
					,C.CATEGORY_NAME
				FROM
					ANNO_BOARD
				JOIN
					ANNO_CATEGORY C USING(CATEGORY_NO)
			 """)
	List<BoardDto> findAll(RowBounds rb);

	@Select("""
			SELECT
				BOARD_NO 
				,BOARD_TITLE 
				,BOARD_CONTENT 
				,BOARD_COUNT 
				,REG_DATE
				,C.CATEGORY_NAME
			FROM
				ANNO_BOARD
			JOIN
				ANNO_CATEGORY C USING(CATEGORY_NO)
			WHERE
				BOARD_NO = #{boardNo}
			""")
	BoardDto findByNo(Long boardNo);
	
	@Update("""
			UPDATE			
				ANNO_BOARD
			SET
				 BOARD_TITLE
				,BOARD_CONTENT
				,
			WHERE
				BOARD_NO = #{boardNo}
			""")
	void editByNo(@Param("board")BoardDto board, @Param("boardNo")Long boardNo);

	void deleteByNo(Long boardNo);

	@Select("SELECT EXISTS(SELECT 1 FROM ANNO_BOARD WHERE USER_ID = #{userId})")
	boolean existsByUserId(String userId);

	@Select("SELECT USER_PWD FROM ANNO_BOARD WHERE USER_ID = #{userId}")
	Board checkPwd(BoardDto inputBoard);

	@Update("UPDATE ANNO_BOARD SET BOARD_COUNT = BOARD_COUNT + 1 WHERE BOARD_NO = #{boardNo}")
	void increaseCount(Long boardNo);

	@Select("""
			SELECT 
				 CATEGORY_NO
				, CATEGORY_NAME
			FROM 
				ANNO_CATEGORY
				""")
	List<Category> categoryInfo();
	
	// BOARD_TITLE, BOARD_CONTENT, USER_ID, USER_PWD, CATEGORY_NO 게시판 작성
	// BOARD_NO, BOARD_TITLE, BOARD_COUNT, 랜덤한 아이디값, REG_DATE, DEL_YN 게시판 목록 조회
	// BOARD_NO, BOARD_TITLE, BOARD_COUNT, 랜덤한 아이디값, REG_DATE, BOARD_CONTENT, DEL_YN 게시판 상세조회
	// BOARD_NO, USER_ID, USER_PWD, BOARD_TITLE, BOARD_CONTENT 게시판 수정
	// BOARD_NO, USER_ID, USER_PWD 게시판 삭제

}
