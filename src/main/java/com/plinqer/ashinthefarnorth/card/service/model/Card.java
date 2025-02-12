package com.plinqer.ashinthefarnorth.card.service.model;

import com.plinqer.ashinthefarnorth._global.entity.BaseEntity;
import com.plinqer.ashinthefarnorth.user.service.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cards")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "image", nullable = false, length = 1000)
    private String image;

    @Column(name = "wrong_count", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int wrongCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Card(String name, String image, User user) {
        this.name = name;
        this.image = image;
        this.user = user;
    }

    public void incrementWrongCount() {
        wrongCount++;
    }

    public void update(String name, String image, User user) {
        this.name = name;
        this.image = image;
        this.user = user;
    }
}
