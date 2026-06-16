package com.kh.semi.admin.model.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.semi.admin.model.dao.AdminMapper;
import com.kh.semi.admin.model.dto.AdminDto;
import com.kh.semi.admin.model.vo.Admin;
import com.kh.semi.board.model.dto.BoardDto;
import com.kh.semi.exception.DuplicateMemberException;
import com.kh.semi.exception.FailSaveException;
import com.kh.semi.exception.FailSignUpException;
import com.kh.semi.token.model.dao.TokenMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {
	private final AdminMapper adminMapper;
	private final PasswordEncoder passwordEncoder;
	private final TokenMapper tokenMapper;
	
	@Transactional
	public void adminSignUp(AdminDto admin) {
		
		int count = adminMapper.countByMemberId(admin.getAdminId());
		
		if(count > 0) {
			throw new DuplicateMemberException("이미 존재하는 아이디입니다.");
		}
		
		Admin adminEntity = Admin.builder()
				.adminId(admin.getAdminId())
				.adminPwd(passwordEncoder.encode(admin.getAdminPwd())).build();
				
		int result = adminMapper.adminSignUp(adminEntity);
		
		if(1 > result) {
			throw new FailSignUpException("가입에 실패했습니다. 이미 존재하는 아이디입니다.");
		}
	}
	
	// Admin 전용 서비스 단
	
		@Transactional
		public List<BoardDto> findAllByAdmin(int page) {
			RowBounds rb = new RowBounds(page * 10, 10);
			List<BoardDto> boardList = adminMapper.findAllByAdmin(rb);
			
			for (BoardDto dto : boardList) {
				dto.setUserId(makeAnimalNickname(dto.getRegDate()));
				dto.setFormattedRegDate(formatRegDate(dto.getRegDate()));
			}
			log.info("보드리스트 : {}", boardList);
			return boardList;
		}
		
		public BoardDto findByAdmin(Long boardNo) {
			return getDeletedBoardNo(boardNo);
		}
		
		private BoardDto getDeletedBoardNo(Long boardNo) {
			BoardDto boardDetail = adminMapper.findByAdmin(boardNo);
			
			if (boardDetail == null) {
				throw new FailSaveException("유효하지 않은 접근입니다.");
			}
			
			boardDetail.setUserId(makeAnimalNickname(boardDetail.getRegDate()));
			
			boardDetail.setFormattedRegDate(formatRegDate(boardDetail.getRegDate()));
			return boardDetail;
		}
		
		@Transactional
		public void editByAdmin(BoardDto board, Long boardNo) {

			adminMapper.editByAdmin(board, boardNo);
		}
			
		@Transactional
		public void deleteByAdmin(BoardDto board, Long boardNo) {

			adminMapper.deleteByAdmin(board, boardNo);
		}
		
		// 랜덤한 동물 이름으로 만들기
		private String makeAnimalNickname(LocalDateTime regDate) {
		    if (regDate == null) {
		        return "익명";
		    }

		    // milliseconds 추출 (0 ~ 999)
		    int millis = regDate.getNano() / 1_000_000;

		    String[] traitFst = {
		        "정신나간", "용감한", "무서운", "행복한", "건방진",
		        "재빠른", "게으른", "신난", "시건방진", "괘씸한"
		    };

		    String[] traitSec = {
		        "원숭이", "강아지", "황소", "개구리", "래트",
		        "물범", "햄스터", "듀공", "돌고래", "기린"
		    };

		    int fstIndex = (millis / 100) % 10;
		    int secIndex = millis % 10;

		    return traitFst[fstIndex] + " " + traitSec[secIndex];
		}
		
		// ===== 날짜 포맷팅 메서드 =====
		private String formatRegDate(LocalDateTime regDate) {
		    if (regDate == null) {
		        return "";
		    }

		    LocalDateTime now = LocalDateTime.now();
		    long daysBetween = ChronoUnit.DAYS.between(regDate.toLocalDate(), now.toLocalDate());

		    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM.dd HH:mm");
		    DateTimeFormatter fullDateFormatter = DateTimeFormatter.ofPattern("yy.MM.dd HH:mm");

		    if (daysBetween == 0) {
		        // 오늘
		        return regDate.format(timeFormatter);
		    } else if (daysBetween == 1) {
		        // 어제
		        return "어제 " + regDate.format(timeFormatter);
		    } else if (regDate.getYear() == now.getYear()) {
		        // 올해
		        return regDate.format(dateTimeFormatter);
		    } else {
		        // 다른 해
		        return regDate.format(fullDateFormatter);
		    }
		}

}
