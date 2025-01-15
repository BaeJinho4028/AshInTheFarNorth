package com.plinqer.ashinthefarnorth.card.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.plinqer.ashinthefarnorth.card.service.model.Card;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(SnakeCaseStrategy.class)
public record CardResponse(
    @Schema(description = "카드 id", example = "1")
    Integer id,

    @Schema(description = "카드 이름", example = "최북단사는최재애쉬")
    String name,

    @Schema(description = "카드 이미지", example = "https://example.com/image.jpg")
    String image,

    @Schema(description = "틀린 횟수", example = "1")
    int wrongCount,

    @Schema(description = "만든 사용자 이름", example = "테오")
    String userName
) {

    public static CardResponse fromCard(Card card) {
        return new CardResponse(
            card.getId(),
            card.getName(),
            card.getImage(),
            card.getWrongCount(),
            card.getUser().getName()
        );
    }
}
