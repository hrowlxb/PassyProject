package com.hrowlxb.passy_backend.user.service;

import com.hrowlxb.passy_backend.common.util.JwtUtil;
import com.hrowlxb.passy_backend.user.domain.User;
import com.hrowlxb.passy_backend.user.dto.UserRequestDto;
import com.hrowlxb.passy_backend.user.dto.UserResponseDto;
import com.hrowlxb.passy_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void signup (UserRequestDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        String encryptedPw = passwordEncoder.encode(dto.getPassword());
        User user = User.builder()
                .email(dto.getEmail())
                .password(encryptedPw)
                .build();
        userRepository.save(user);
    }

    public UserResponseDto login(UserRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 틀렸습니다."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("이메 또는 비밀번호가 틀렸습니다.");
        }

        String token = jwtUtil.createToken(user.getEmail());
        return new UserResponseDto(token);
    }
}
