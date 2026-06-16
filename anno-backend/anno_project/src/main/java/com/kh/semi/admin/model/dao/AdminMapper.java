package com.kh.semi.admin.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.kh.semi.admin.model.vo.Admin;
import com.kh.semi.board.model.dto.BoardDto;

@Mapper
public interface AdminMapper {
	
	@Insert("INSERT INTO ANNO_ADMIN (ADMIN_ID, ADMIN_PWD, ROLE) VALUES (#{adminId}, #{adminPwd}, 'ROLE_ADMIN')")
	int adminSignUp(Admin admin);

	@Select("SELECT COUNT(*) FROM ANNO_ADMIN WHERE ADMIN_ID = #{adminId}")
	int countByMemberId(String adminId);
	
	// 어드민 조회, 삭제, 수정 기능
	
		@Select("""
				SELECT
					BOARD_NO 
					,BOARD_TITLE 
					,BOARD_CONTENT 
					,BOARD_COUNT 
					,CATEGORY_NO
					,REG_DATE
					,DEL_YN
					,C.CATEGORY_NAME
				FROM
					ANNO_BOARD
				JOIN
					ANNO_CATEGORY C USING(CATEGORY_NO)
				ORDER
				BY
					REG_DATE DESC
			 """)
		List<BoardDto> findAllByAdmin(RowBounds rb);
		
		@Update("""
				UPDATE			
					ANNO_BOARD
				SET
					 BOARD_TITLE	= #{board.boardTitle}
					,BOARD_CONTENT	= #{board.boardContent}
					,CATEGORY_NO	= #{board.categoryNo}
				WHERE
					BOARD_NO = #{boardNo}
				""")
		void editByAdmin(@Param("board")BoardDto board, @Param("boardNo")Long boardNo);

		@Update("UPDATE ANNO_BOARD SET DEL_YN = 'Y' WHERE BOARD_NO = #{boardNo}")
		void deleteByAdmin(@Param("board")BoardDto board, @Param("boardNo")Long boardNo);
		
		@Select("""
				SELECT
					BOARD_NO 
					,BOARD_TITLE 
					,BOARD_CONTENT 
					,BOARD_COUNT 
					,REG_DATE
					,DEL_YN
					,C.CATEGORY_NAME
				FROM
					ANNO_BOARD
				JOIN
					ANNO_CATEGORY C USING(CATEGORY_NO)
				WHERE
					BOARD_NO = #{boardNo}
				""")
		BoardDto findByAdmin(Long boardNo);
		
}
