package com.technonext.transport.common.exception;

import com.technonext.transport.common.constant.ApplicationConstant;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Setter
@Getter
public class CommonServerException extends RuntimeException {
    /**
     * Serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1436995162658277359L;
    /**
     * Error id.
     */
    private final String errorId;

    /**
     * trace id.
     */
    private final String traceId;

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public CommonServerException(String errorId, HttpStatus status, String traceId) {
        this.errorId = errorId;
        this.traceId = traceId;
        this.status = status;
    }

    public static CommonServerException badRequest(String errorId) {
        return new CommonServerException(errorId, HttpStatus.BAD_REQUEST, MDC.get(
                ApplicationConstant.TRACE_ID));
    }

    public static CommonServerException notFound(String errorId) {
        return new CommonServerException(errorId, HttpStatus.NOT_FOUND, MDC.get(
                ApplicationConstant.TRACE_ID));
    }

    public static CommonServerException dataSaveException(String errorId) {
        return new CommonServerException(errorId, HttpStatus.INTERNAL_SERVER_ERROR,
            MDC.get(ApplicationConstant.TRACE_ID));
    }

    public static CommonServerException internalServerException(String errorId) {
        return new CommonServerException(errorId, HttpStatus.INTERNAL_SERVER_ERROR,
                MDC.get(ApplicationConstant.TRACE_ID));
    }

    public static CommonServerException methodNotAllowed(String errorId) {
        return new CommonServerException(errorId, HttpStatus.UNAUTHORIZED,
            MDC.get(ApplicationConstant.TRACE_ID));
    }

    public static CommonServerException notAuthorized(String errorId) {
        return new CommonServerException(
                errorId,
                HttpStatus.FORBIDDEN,
                MDC.get(ApplicationConstant.TRACE_ID)
        );
    }
}
