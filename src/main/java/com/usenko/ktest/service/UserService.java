package com.usenko.ktest.service;

import com.usenko.ktest.dto.NewUserRequest;
import com.usenko.ktest.dto.UserDto;

public interface UserService {
    UserDto createUser(NewUserRequest newUser);
}
