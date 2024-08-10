package com.example.TripChat.service;

import com.example.TripChat.entity.Users;
import com.example.TripChat.repository.UserRepository;
import com.example.TripChat.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(UserDTO userDTO) {
        Users user = Users.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .nationality(userDTO.getNationality())
                .build();
        userRepository.save(user);
    }

    public UserDTO findByUsername(String username) {
        Users user = userRepository.findByUsername(username);
        if (user != null) {
            return new UserDTO(user.getUsername(), user.getPassword(), user.getEmail(), user.getNationality());
        }
        return null;
    }
}