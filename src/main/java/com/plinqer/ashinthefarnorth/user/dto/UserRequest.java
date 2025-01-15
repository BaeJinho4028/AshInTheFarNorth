package com.plinqer.ashinthefarnorth.user.dto;

import com.plinqer.ashinthefarnorth.user.service.model.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
    @Schema(description = "사용자 이름", example = "테오", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(max = 10, message = "사용자 이름의 길이는 최대 10자입니다.")
    @NotBlank(message = "사용자 이름을 입력해주세요.")
    String name
) {

    public User toUser() {
        return new User(
            name
        );
    }
}
