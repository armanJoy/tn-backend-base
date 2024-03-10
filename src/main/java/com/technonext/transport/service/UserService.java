package com.technonext.transport.service;

import com.technonext.transport.model.user.User;
import com.technonext.transport.dto.request.UserRequestDto;
import com.technonext.transport.dto.response.UserResponseDto;

import java.util.Set;

public interface UserService {
    User findByUserName(String username);

    User findByUserId(Long userId);
    UserResponseDto createUser(UserRequestDto requestDto, Long roleId);
//    User save(UserRequestDto requestDto);

    Set<User> findAllUserByIdIn(Set<Long> userIds, Boolean isActive);
}
