package com.plinqer.ashinthefarnorth.user.dto;

import com.plinqer.ashinthefarnorth.user.service.model.User;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserResponse(
    @Schema(description = "사용자 id", example = "1")
    Integer id,

    @Schema(description = "사용자 이름", example = "테오")
    String name,

    @Schema(description = "승리 횟수", example = "7")
    int winCount
) {

    public static UserResponse fromUser(User user) {
        return new UserResponse(
            user.getId(),
            user.getName(),
            user.getWinCount()
        );
    }
}
