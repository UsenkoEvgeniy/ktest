package com.usenko.ktest.service;

import com.usenko.ktest.dto.NewUserRequest;
import com.usenko.ktest.dto.UserDto;
import com.usenko.ktest.model.User;
import com.usenko.ktest.model.mapper.UserMapper;
import com.usenko.ktest.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto createUser(NewUserRequest newUser){
        User user = userRepository.save(UserMapper.newUserToUser(newUser));
        return UserMapper.toUserDto(user);
    }
}
