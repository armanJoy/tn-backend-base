package com.technonext.transport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {

    private String message;

    private boolean isError = false;

    private Object data;

}
