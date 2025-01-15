package com.plinqer.ashinthefarnorth.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plinqer.ashinthefarnorth.user.dto.UserRequest;
import com.plinqer.ashinthefarnorth.user.dto.UserResponse;
import com.plinqer.ashinthefarnorth.user.service.model.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
            .map(UserResponse::fromUser)
            .toList();
    }

    public UserResponse getUserById(Integer id) {
        User user = getById(id);
        return UserResponse.fromUser(user);
    }

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        User user = userRequest.toUser();
        User createdUser = userRepository.save(user);
        return UserResponse.fromUser(createdUser);
    }

    @Transactional
    public UserResponse incrementWinCount(Integer id) {
        User user = getById(id);
        user.incrementWinCount();
        return UserResponse.fromUser(user);
    }

    @Transactional
    public UserResponse modifyUser(Integer id, UserRequest userRequest) {
        User user = getById(id);
        user.update(userRequest.name());
        return UserResponse.fromUser(user);
    }

    @Transactional
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    User getById(Integer id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }
}
