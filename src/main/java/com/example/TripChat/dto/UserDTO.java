package com.example.TripChat.dto;

import com.example.TripChat.enums.Nationality;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private Nationality nationality;
}
