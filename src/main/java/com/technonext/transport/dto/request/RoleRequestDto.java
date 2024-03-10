package com.technonext.transport.dto.request;

import com.technonext.transport.generic.payload.request.IDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestDto implements IDto {

    @Schema(example = "[1,2]")
    private Set<Long> userIds;

    @Schema(example = "SUPER_ADMIN")
    @NotBlank
    private String name;

}
