package com.hrowlxb.passy_backend.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequest {

    @Email(message = "올바른 이메일 형식을 입력해주세요.")
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Size(min = 8, max = 24, message = "비밀번호는 8자리에서 24자리사이로 입력해주세요.")
    private String password;

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    @Size(max = 7, message = "닉네임은 7자 이내로 입력해주세요.")
    private String nickname;
}
