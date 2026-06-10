package com.kh.tv.member.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {
	private Long memberNo;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String role;
	private String delYn;
	private Date regDate;
}
