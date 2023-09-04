package com.lgs.usermgmt;

import com.lgs.usermgmt.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class UserMgmtAppControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleCityNotFoundException(
            RuntimeException ex, WebRequest request) {
        log.error("Runtime Exception Occurred",ex);
        ErrorResponse errorResponse = ErrorResponse.builder().message("Server error occurred. Please contact admin").build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
