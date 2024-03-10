package com.technonext.transport.controller;

import com.technonext.transport.common.ApiConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = ApiConstants.TEST)
public class TestController {

    @GetMapping(ApiConstants.PING)
    public String checkPing(@RequestParam String test) {
        return "Transport Management API Ping Returned 200!";
    }
}
