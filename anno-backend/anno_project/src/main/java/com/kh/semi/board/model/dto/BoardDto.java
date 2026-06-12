package com.kh.semi.board.model.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class BoardDto {
	private Long boardNo; // always generate as identity primary key > 보드넘버 뽑기용
	@NotBlank(message = "공백은 불가능합니다.")
    private String boardTitle;
	@NotBlank
    private String boardContent;
    private Long boardCount;
    @NotBlank
    @Size(min =4, max =20, message = "아이디는 4글자 이상만 제발!")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "영문자와 숫자만 입력 가능합니다.")
    private String userId;
    @NotBlank
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 설정하세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", 
             message = "비밀번호는 영문, 숫자, 특수문자를 포함해야 합니다.")
    private String userPwd;
    private int categoryNo;
    private String delYn;
    private LocalDateTime regDate; 
    
}
