package com.plinqer.ashinthefarnorth.card.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plinqer.ashinthefarnorth.card.dto.CardRequest;
import com.plinqer.ashinthefarnorth.card.dto.CardResponse;
import com.plinqer.ashinthefarnorth.card.service.model.Card;
import com.plinqer.ashinthefarnorth.user.service.UserService;
import com.plinqer.ashinthefarnorth.user.service.model.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CardService {

    private final UserService userService;
    private final CardRepository cardRepository;

    public List<CardResponse> getAllCards() {
        List<Card> cards = cardRepository.findAll();
        return cards.stream()
            .map(CardResponse::fromCard)
            .toList();
    }

    public CardResponse getCardById(Integer id) {
        Card card = getById(id);
        return CardResponse.fromCard(card);
    }

    @Transactional
    public List<CardResponse> createCards(List<CardRequest> requests) {
        List<Card> cards = requests.stream()
            .map(request -> request.toCard(userService.getById(request.userId())))
            .toList();
        List<Card> createdCards = cardRepository.saveAll(cards);
        return createdCards.stream()
            .map(CardResponse::fromCard)
            .toList();
    }

    @Transactional
    public CardResponse createCard(CardRequest cardRequest) {
        User user = userService.getById(cardRequest.userId());
        Card card = cardRequest.toCard(user);
        Card createdCard = cardRepository.save(card);
        return CardResponse.fromCard(createdCard);
    }

    @Transactional
    public CardResponse incrementWrongCount(Integer id) {
        Card card = getById(id);
        card.incrementWrongCount();
        return CardResponse.fromCard(card);
    }

    @Transactional
    public CardResponse modifyCard(Integer id, CardRequest cardRequest) {
        User user = userService.getById(cardRequest.userId());
        Card card = getById(id);
        card.update(
            cardRequest.name(),
            cardRequest.image(),
            user
        );
        return CardResponse.fromCard(card);
    }

    @Transactional
    public void deleteCard(Integer id) {
        cardRepository.deleteById(id);
    }

    private Card getById(Integer id) {
        return cardRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Card not found with id: " + id));
    }
}
