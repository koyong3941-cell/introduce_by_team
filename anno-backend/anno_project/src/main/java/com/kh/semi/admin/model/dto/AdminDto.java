package com.kh.semi.admin.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminDto {
	private String adminId;
	private String adminPwd;
	private String role; 
	private Date createDate;
	private String delYn;

}
