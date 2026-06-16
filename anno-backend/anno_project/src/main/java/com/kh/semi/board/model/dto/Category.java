package com.kh.semi.board.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
@Builder
public class Category {
	private int categoryNo;
	private String categoryName;
}
