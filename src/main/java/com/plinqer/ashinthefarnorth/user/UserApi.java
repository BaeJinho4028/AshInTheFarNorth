package com.plinqer.ashinthefarnorth.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.plinqer.ashinthefarnorth.user.dto.UserRequest;
import com.plinqer.ashinthefarnorth.user.dto.UserResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "User", description = "사용자 관리 API")
public interface UserApi {

    @Operation(summary = "모든 사용자 조회")
    @ApiResponses({
        @ApiResponse(responseCode = "200")
    })
    @GetMapping("/users")
    ResponseEntity<List<UserResponse>> getAllUsers();

    @Operation(summary = "특정 사용자 조회")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/user/{id}")
    ResponseEntity<UserResponse> getUserById(
        @PathVariable Integer id
    );

    @Operation(summary = "새 사용자 생성")
    @ApiResponses({
        @ApiResponse(responseCode = "201")
    })
    @PostMapping("/user")
    ResponseEntity<UserResponse> createUser(
        @RequestBody @Valid UserRequest userRequest
    );

    @Operation(summary = "사용자의 승리 횟수 증가")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/user/{id}/win")
    ResponseEntity<UserResponse> incrementWinCount(
        @PathVariable Integer id
    );

    @Operation(summary = "사용자 정보 업데이트")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("/user/{id}")
    ResponseEntity<UserResponse> modifyUser(
        @PathVariable Integer id,
        @RequestBody @Valid UserRequest userRequest
    );

    @Operation(summary = "특정 사용자 삭제")
    @ApiResponses({
        @ApiResponse(responseCode = "204"),
        @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping("/user/{id}")
    ResponseEntity<Void> deleteUser(
        @PathVariable Integer id
    );
}
