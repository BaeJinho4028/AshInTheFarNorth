package com.plinqer.ashinthefarnorth.card;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.plinqer.ashinthefarnorth.card.dto.CardRequest;
import com.plinqer.ashinthefarnorth.card.dto.CardResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Card", description = "카드 관리 API")
public interface CardApi {

    @Operation(summary = "모든 카드 조회")
    @ApiResponses({
        @ApiResponse(responseCode = "200")
    })
    @GetMapping("/cards")
    ResponseEntity<List<CardResponse>> getAllCards();

    @Operation(summary = "특정 카드 조회")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/card/{id}")
    ResponseEntity<CardResponse> getCardById(
        @PathVariable Integer id
    );

    @Operation(summary = "새 카드 리스트 생성")
    @ApiResponses({
        @ApiResponse(responseCode = "201")
    })
    @PostMapping("/cards")
    ResponseEntity<List<CardResponse>> createCards(
        @RequestBody @Valid List<CardRequest> request
    );

    @Operation(summary = "새 카드 생성")
    @ApiResponses({
        @ApiResponse(responseCode = "201")
    })
    @PostMapping("/card")
    ResponseEntity<CardResponse> createCard(
        @RequestBody @Valid CardRequest cardRequest
    );

    @Operation(summary = "카트 틀린 횟수 증가")
    @ApiResponses({
        @ApiResponse(responseCode = "201")
    })
    @PostMapping("/card/{id}/wrong")
    ResponseEntity<CardResponse> incrementWrongCount(
        @PathVariable Integer id
    );

    @Operation(summary = "카드 정보 업데이트")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("/card/{id}")
    ResponseEntity<CardResponse> modifyCard(
        @PathVariable Integer id,
        @RequestBody @Valid CardRequest cardRequest
    );

    @Operation(summary = "특정 카드 삭제")
    @ApiResponses({
        @ApiResponse(responseCode = "204"),
    })
    @DeleteMapping("/card/{id}")
    ResponseEntity<Void> deleteCard(
        @PathVariable Integer id
    );
}
