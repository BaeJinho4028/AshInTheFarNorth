package com.plinqer.ashinthefarnorth.card;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plinqer.ashinthefarnorth.card.dto.CardRequest;
import com.plinqer.ashinthefarnorth.card.dto.CardResponse;
import com.plinqer.ashinthefarnorth.card.service.CardService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CardController implements CardApi {

    private final CardService cardService;

    @Operation(summary = "모든 카드 조회")
    @GetMapping("/cards")
    public ResponseEntity<List<CardResponse>> getAllCards() {
        List<CardResponse> cards = cardService.getAllCards();
        return ResponseEntity.status(HttpStatus.OK).body(cards);
    }

    @Operation(summary = "특정 카드 조회")
    @GetMapping("/card/{id}")
    public ResponseEntity<CardResponse> getCardById(@PathVariable Integer id) {
        CardResponse card = cardService.getCardById(id);
        return ResponseEntity.status(HttpStatus.OK).body(card);
    }

    @Operation(summary = "새 카드 생성")
    @PostMapping("/card")
    public ResponseEntity<CardResponse> createCard(@RequestBody @Valid CardRequest cardRequest) {
        CardResponse createdCard = cardService.createCard(cardRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCard);
    }

    @Operation(summary = "카드 틀린 횟수 증가")
    @PostMapping("/card/{id}/wrong")
    public ResponseEntity<CardResponse> incrementWrongCount(@PathVariable Integer id) {
        CardResponse updatedCard = cardService.incrementWrongCount(id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCard);
    }

    @Operation(summary = "카드 정보 업데이트")
    @PutMapping("/card/{id}")
    public ResponseEntity<CardResponse> modifyCard(
        @PathVariable Integer id,
        @RequestBody @Valid CardRequest cardRequest
    ) {
        CardResponse modifiedCard = cardService.modifyCard(id, cardRequest);
        return ResponseEntity.status(HttpStatus.OK).body(modifiedCard);
    }

    @Operation(summary = "특정 카드 삭제")
    @DeleteMapping("/card/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Integer id) {
        cardService.deleteCard(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
