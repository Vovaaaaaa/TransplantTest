package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private long userId;

    private String userName;

    private int userAge;

    public static User toUser(UserDto userDto) {
        return User.builder()
                .userId(Long.toString(userDto.getUserId()))
                .userName(userDto.getUserName())
                .userAge(userDto.getUserAge())
                .build();
    }

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .userId(Long.parseLong(user.getUserId()))
                .userName(user.getUserName())
                .userAge(user.getUserAge())
                .build();
    }

}
