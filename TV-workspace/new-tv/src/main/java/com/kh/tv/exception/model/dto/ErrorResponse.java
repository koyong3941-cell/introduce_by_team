package com.kh.tv.exception.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse<T> {
	private int code;
	private String message;
	private T data;
}
