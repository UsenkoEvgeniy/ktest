package com.usenko.ktest.model.mapper;

import com.usenko.ktest.dto.NewUserRequest;
import com.usenko.ktest.dto.UserDto;
import com.usenko.ktest.model.User;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class UserMapper {
    public User newUserToUser(NewUserRequest newUser) {
        User user = new User();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setCreationDate(LocalDateTime.now());
        user.setPassword(newUser.getPassword());
        return user;
    }

    public UserDto toUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCreationDate(user.getCreationDate());
        return dto;
    }
}
