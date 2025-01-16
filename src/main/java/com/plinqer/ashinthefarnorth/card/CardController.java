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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CardController implements CardApi {

    private final CardService cardService;

    @GetMapping("/cards")
    public ResponseEntity<List<CardResponse>> getAllCards() {
        List<CardResponse> cards = cardService.getAllCards();
        return ResponseEntity.status(HttpStatus.OK).body(cards);
    }

    @GetMapping("/card/{id}")
    public ResponseEntity<CardResponse> getCardById(
        @PathVariable Integer id
    ) {
        CardResponse card = cardService.getCardById(id);
        return ResponseEntity.status(HttpStatus.OK).body(card);
    }

    @PostMapping("/cards")
    public ResponseEntity<List<CardResponse>> createCards(
        @RequestBody @Valid List<CardRequest> request
    ) {
        List<CardResponse> response = cardService.createCards(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/card")
    public ResponseEntity<CardResponse> createCard(
        @RequestBody @Valid CardRequest request
    ) {
        CardResponse createdCard = cardService.createCard(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCard);
    }

    @PostMapping("/card/{id}/wrong")
    public ResponseEntity<CardResponse> incrementWrongCount(
        @PathVariable Integer id
    ) {
        CardResponse updatedCard = cardService.incrementWrongCount(id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCard);
    }

    @PutMapping("/card/{id}")
    public ResponseEntity<CardResponse> modifyCard(
        @PathVariable Integer id,
        @RequestBody @Valid CardRequest request
    ) {
        CardResponse modifiedCard = cardService.modifyCard(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(modifiedCard);
    }

    @DeleteMapping("/card/{id}")
    public ResponseEntity<Void> deleteCard(
        @PathVariable Integer id
    ) {
        cardService.deleteCard(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
