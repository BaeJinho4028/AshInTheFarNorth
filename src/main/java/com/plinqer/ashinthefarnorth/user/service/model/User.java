package com.plinqer.ashinthefarnorth.user.service.model;

import com.plinqer.ashinthefarnorth._global.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "win_count", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int winCount = 0;

    public User(String name) {
        this.name = name;
    }

    public void incrementWinCount() {
        winCount++;
    }

    public void update(String name) {
        this.name = name;
    }
}
