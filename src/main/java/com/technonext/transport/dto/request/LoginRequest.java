package com.technonext.transport.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Schema(description = "give your username", example = "admin")
    @NotBlank(message = "username required")
    private String username;

    @Schema(description = "give password", example = "admin")
    @NotBlank(message = "password required")
    private String password;
}
