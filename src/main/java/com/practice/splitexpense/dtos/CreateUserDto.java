package com.practice.splitexpense.dtos;

import com.practice.splitexpense.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserDto {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    public User toUser() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .build();
    }
}
