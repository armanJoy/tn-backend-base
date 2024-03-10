package com.technonext.transport.dto.request;

import com.technonext.transport.generic.payload.request.IDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto implements IDto {

    @NotEmpty(message = "username is required")
    private String username;

    @NotEmpty(message = "email is required")
    @Email
    private String email;

    @NotEmpty(message = "name is required")
    private String name;

//    @NotEmpty(message = "role is required")
//    private String role;

    @NotEmpty(message = "password is required")
    @Size(min = 6)
    private String password;
}
