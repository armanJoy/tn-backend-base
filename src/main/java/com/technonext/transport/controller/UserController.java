package com.technonext.transport.controller;

import com.technonext.transport.common.ApiConstants;
import com.technonext.transport.dto.request.UserRequestDto;
import com.technonext.transport.dto.response.UserResponseDto;
import com.technonext.transport.model.user.RoleEnum;
import com.technonext.transport.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@Tag(name = ApiConstants.USER)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(ApiConstants.CREATE_USER)
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto request) {
        return new ResponseEntity<>(userService.createUser(request, null), HttpStatus.OK);
    }

    @PostMapping(ApiConstants.CREATE_ADMIN_USER)
    public ResponseEntity<UserResponseDto> createAdminUser(@Valid @RequestBody UserRequestDto request) {
        return new ResponseEntity<>(userService.createUser(request, RoleEnum.ADMIN.getValue()), HttpStatus.OK);
    }
}
