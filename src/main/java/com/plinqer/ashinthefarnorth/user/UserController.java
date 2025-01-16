package com.plinqer.ashinthefarnorth.user;

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

import com.plinqer.ashinthefarnorth.user.dto.UserRequest;
import com.plinqer.ashinthefarnorth.user.dto.UserResponse;
import com.plinqer.ashinthefarnorth.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> response = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUserById(
        @PathVariable Integer id
    ) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    @PostMapping("/user")
    public ResponseEntity<UserResponse> createUser(
        @RequestBody @Valid UserRequest request
    ) {
        UserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @PostMapping("/user/{id}/win")
    public ResponseEntity<UserResponse> incrementWinCount(
        @PathVariable Integer id
    ) {
        UserResponse response = userService.incrementWinCount(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    @PutMapping("/user/{id}")
    public ResponseEntity<UserResponse> modifyUser(
        @PathVariable Integer id,
        @RequestBody @Valid UserRequest request
    ) {
        UserResponse response = userService.modifyUser(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(
        @PathVariable Integer id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
