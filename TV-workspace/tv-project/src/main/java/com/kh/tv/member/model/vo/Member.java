package com.kh.tv.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class Member {
	
	private Long memberNo;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String role;
	private String delYn;
	private Date regDate;
}
