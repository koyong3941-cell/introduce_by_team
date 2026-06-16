package com.kh.semi.board.model.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Board {
	private Long boardNo; // always generate as identity primary key > 보드넘버 뽑기용
    private String boardTitle;
    private String boardContent;
    private Long boardCount;
    private String userId;
    private String userPwd;
    private int categoryNo;
    private String delYn;
    private LocalDateTime regDate;
}
