package com.plinqer.ashinthefarnorth.user.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plinqer.ashinthefarnorth.user.service.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
