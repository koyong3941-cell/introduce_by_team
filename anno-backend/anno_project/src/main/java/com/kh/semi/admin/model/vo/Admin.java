package com.kh.semi.admin.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class Admin {
	private String adminId;
	private String adminPwd;
	private String role; 
	private Date createDate;
	private String delYn;

}
