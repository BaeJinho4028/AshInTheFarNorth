package com.plinqer.ashinthefarnorth.card.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plinqer.ashinthefarnorth.card.service.model.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {

}
