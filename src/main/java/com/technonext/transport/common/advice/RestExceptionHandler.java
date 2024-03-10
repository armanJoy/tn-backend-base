package com.technonext.transport.common.advice;


import com.technonext.transport.common.constant.ApplicationConstant;
import com.technonext.transport.common.constant.ErrorId;
import com.technonext.transport.common.exception.ApiError;
import com.technonext.transport.common.exception.ErrorCodeReader;
import com.technonext.transport.common.exception.ErrorDto;
import com.technonext.transport.common.exception.CommonServerException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.*;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError();
        ErrorDto ErrorDto =
                new ErrorDto(ErrorId.SYSTEM_ERROR, ex.getLocalizedMessage());
        apiError.addError(ErrorDto);
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object>
    handleConstraintViolationExceptionAllException(ConstraintViolationException ex, WebRequest request) {
        ApiError apiError = new ApiError();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        violations.forEach(violation -> {
            ErrorDto reservationError = getError(violation.getMessageTemplate());
            apiError.addError(reservationError);
        });
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommonServerException.class)
    public final ResponseEntity<Object> handleServerException(
            CommonServerException ex, WebRequest request) {
        ApiError apiError = new ApiError();
        ErrorDto reservationError = getError(ex.getErrorId());
        apiError.addError(reservationError);
        return new ResponseEntity<>(apiError, ex.getStatus());
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        ApiError apiError = new ApiError();
//
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            ErrorDto reservationError = getError(error.getCode());
//            apiError.addError(reservationError);
//        }
//        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
//    }

    private ErrorDto getError(String code) {
        ErrorDto ErrorDto = ErrorCodeReader.getErrorByCode(code);
        if (Objects.isNull(ErrorDto)) {
            return ErrorCodeReader.getErrorByMessage(code);
        }
        return ErrorDto;
    }


    private String buildErrorMessage(FieldError error) {
        return capitalize(StringUtils.join(splitByCharacterTypeCamelCase(emptyFieldErrorIfNull(error)
        ), SPACE)) + SPACE + error.getDefaultMessage();
    }

    private String emptyFieldErrorIfNull(FieldError fieldError) {
        return Objects.isNull(fieldError) ? ApplicationConstant.EMPTY_STRING : fieldError.getField();
    }
}
