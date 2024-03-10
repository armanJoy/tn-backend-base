package com.technonext.transport.common.Utils;


import com.technonext.transport.common.constant.ApplicationConstant;
import com.technonext.transport.config.authentication.service.CustomUserDetails;
import com.technonext.transport.model.user.User;
import com.technonext.transport.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;


@Component
public class Helper {

    protected static final Logger LOGGER = LoggerFactory.getLogger(Helper.class);
    private static UserService userService;
    private static CustomUserDetails loggedInUser;

    @Autowired
    public Helper(UserService userService) {
        Helper.userService = userService;
    }

    public static User getCurrentUser() {
        User user =  new User();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            if (authentication.getPrincipal() instanceof String) {
                if (loggedInUser == null || !authentication.getPrincipal().equals(loggedInUser.getUsername())) {
                    assert loggedInUser != null;
                    user.setId(loggedInUser.getId());
                    return user;
                }
            }
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            user.setId(userDetails.getId());
            return user;
        }
        return null;
    }

    public static String createDynamicCode(String errorCode, String... placeHolders) {
        StringBuilder builder = new StringBuilder(errorCode);
        if (Objects.isNull(placeHolders)) {
            return errorCode;
        }
        Arrays.stream(placeHolders).forEach(placeHolder ->
                builder.append(ApplicationConstant.MESSAGE_SEPARATOR).append(placeHolder));
        return builder.toString();
    }
}
