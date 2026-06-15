package com.kh.semi.admin.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AdminDto {
	private String adminId;
	private String adminPwd;
	private String role; 
	private Date createDate;
	private String status;

}
