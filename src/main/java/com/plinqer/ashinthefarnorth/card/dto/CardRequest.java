package com.plinqer.ashinthefarnorth.card.dto;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.plinqer.ashinthefarnorth.card.service.model.Card;
import com.plinqer.ashinthefarnorth.user.service.model.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonNaming(SnakeCaseStrategy.class)
public record CardRequest(
    @Schema(description = "카드 이름", example = "최북단사는최재애쉬", requiredMode = REQUIRED)
    @Size(max = 30, message = "카드 이름의 길이는 최대 30자입니다.")
    @NotBlank(message = "카드 이름을 입력해주세요.")
    String name,

    @Schema(description = "카드 이미지", example = "https://example.com/image.jpg", requiredMode = REQUIRED)
    @Size(max = 1000, message = "카드 이미지 URL의 길이는 최대 1000자입니다.")
    @NotBlank(message = "카드 이미지 URL을 입력해주세요.")
    String image,

    @Schema(description = "만든 사용자 id", example = "1", requiredMode = REQUIRED)
    @NotNull(message = "만든 사용자 id를 입력해주세요.")
    Integer userId
) {

    public Card toCard(User user) {
        return new Card(
            name,
            image,
            user
        );
    }
}
