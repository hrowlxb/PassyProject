package com.hrowlxb.passy_backend.user.controller;

import com.hrowlxb.passy_backend.user.dto.UpdateUserRequest;
import com.hrowlxb.passy_backend.user.dto.UserLoginRequest;
import com.hrowlxb.passy_backend.user.dto.UserResponse;
import com.hrowlxb.passy_backend.user.dto.UserSignupRequest;
import com.hrowlxb.passy_backend.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody UserSignupRequest request) {
        userService.signup(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginRequest request) {
        String token = userService.login(request);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyInfo(Authentication authentication) {
        String email = authentication.getName();
        UserResponse response = userService.getMyInfo(email);
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<String> updateUser(@RequestBody @Valid UpdateUserRequest request,
                                             Authentication authentication) {
        String email = authentication.getName();
        userService.updateUserInfo(email, request.getNickname());
        return ResponseEntity.ok("회원 정보가 수정되었습니다.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(Authentication authentication) {
        String email = authentication.getName();
        userService.deleteUser(email);
        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
    }
}
