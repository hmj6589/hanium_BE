package com.example.TripChat.controller;

import com.example.TripChat.service.UserService;
import com.example.TripChat.dto.ResponseDTO;
import com.example.TripChat.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
        if (userService.findByUsername(userDTO.getUsername()) != null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "Username 이 이미 존재합니다."));
        }
        userService.registerUser(userDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(HttpStatus.OK.value(), "회원가입에 성공했습니다."));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody UserDTO userDTO) {
        UserDTO foundUser = userService.findByUsername(userDTO.getUsername());
        if (foundUser != null && passwordEncoder.matches(userDTO.getPassword(), foundUser.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(HttpStatus.OK.value(), "로그인에 성공했습니다."));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "username, password를 다시 확인해주세요!"));
    }
}
