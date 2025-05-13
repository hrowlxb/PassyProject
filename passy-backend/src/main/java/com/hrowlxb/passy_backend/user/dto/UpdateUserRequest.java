package com.hrowlxb.passy_backend.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequest {

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    @Size(max = 7, message = "닉네임은 7자 이내여야 합니다.")
    private String nickname;
}
