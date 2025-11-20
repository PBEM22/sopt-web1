package org.sopt.web1.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.sopt.web1.global.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse<Void> response = ApiResponse.fail(errorCode.getCode(), errorCode.getMsg());

        log.warn(errorCode.getMsg(), e);

        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    /**
     * 404 (NoHandlerFoundException) 핸들링
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNoHandlerFoundException(NoHandlerFoundException e) {
        ApiResponse<Void> response = ApiResponse.fail(
                HttpStatus.NOT_FOUND.value(),
                ErrorCode.NOT_FOUND_URL.getMsg()
        );

        log.error("Not Found URL: {}", e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {

        ApiResponse<Void> response = ApiResponse.fail(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ErrorCode.INTERNAL_SERVER_ERROR.getMsg()
        );

        log.error("Unhandled Exception: {}", e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
