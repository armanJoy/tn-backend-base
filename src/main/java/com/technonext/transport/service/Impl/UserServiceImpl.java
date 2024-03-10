package com.technonext.transport.service.Impl;

import com.technonext.transport.common.constant.ErrorId;
import com.technonext.transport.common.exception.CommonServerException;
import com.technonext.transport.config.authentication.service.CustomUserDetails;
import com.technonext.transport.mapper.UserMapper;
import com.technonext.transport.model.user.Role;
import com.technonext.transport.model.user.User;
import com.technonext.transport.dto.request.UserRequestDto;
import com.technonext.transport.dto.response.UserResponseDto;
import com.technonext.transport.repository.RoleRepository;
import com.technonext.transport.repository.UserRepository;
import com.technonext.transport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public User findByUserName(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> CommonServerException.notFound(ErrorId.NOT_FOUND));
    }

    @Override
    public User findByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> CommonServerException.notFound(""));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return CustomUserDetails.build(user.get());
        } else {
            throw new UsernameNotFoundException("User Not Found" + username);
        }
    }

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto, Long roleId) {
        Role role = roleRepository.findById(roleId).get();
        User user = userMapper.convertToEntity(requestDto,role);
        return userMapper.convertToResponse(userRepository.save(user));
    }


//    @Override
//    @Transactional
//    public User save(UserRequestDto requestDto) {
//        User user = userMapper.convertToEntity(requestDto);
//        return userRepository.save(user);
//    }

    @Override
    public Set<User> findAllUserByIdIn(Set<Long> userIds, Boolean isActive) {
        return userRepository.findAllByIdInAndIsActive(userIds, isActive);
    }
}
