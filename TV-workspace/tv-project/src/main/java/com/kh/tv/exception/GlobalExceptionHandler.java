package com.kh.tv.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kh.tv.exception.model.dto.ErrorResponse;

public class GlobalExceptionHandler {
	
	@ExceptionHandler(CountDuplicationException.class)
	public ResponseEntity<ErrorResponse> handlerDuplicateId(CountDuplicationException e) {
		ErrorResponse er = new ErrorResponse(400, e.getMessage(), null);
		return ResponseEntity.badRequest().body(er);
	}

	@ExceptionHandler(FailSignUpException.class)
	public ResponseEntity<ErrorResponse> handlerFailSignUp(FailSignUpException e) {
		return ResponseEntity.internalServerError().body(new ErrorResponse(500, e.getMessage(), null));
	}
	
	
	
}
