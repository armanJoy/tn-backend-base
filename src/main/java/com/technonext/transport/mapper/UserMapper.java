package com.technonext.transport.mapper;

import com.technonext.transport.model.user.Role;
import com.technonext.transport.model.user.User;
import com.technonext.transport.dto.request.UserRequestDto;
import com.technonext.transport.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserMapper {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto convertToResponse(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .createdAt(user.getCreatedTime())
                .updatedAt(user.getUpdatedTime())
                .createdById((Objects.nonNull(user.getCreatedBy())) ? user.getCreatedBy().getId() : null)
                .updatedById(Objects.nonNull(user.getUpdatedBy())? user.getUpdatedBy().getId() : null)
                .isActive(user.getIsActive())

                .build();
    }

    public User convertToEntity(UserRequestDto dto, Role role) {
        User entity = new User();
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setFullName(dto.getName());
        entity.setRole(role);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        return entity;
    }
}
