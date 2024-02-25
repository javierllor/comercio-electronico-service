package com.gft.commercial.exception;

import com.gft.commercial.swagger.dto.ApiErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ApiErrorDto handleValidationException(ValidationException exception) {
        logger.error("Validation error occurred: {}", exception.getMessage());
        return ApiErrorDto.builder().errorCode(exception.getErrorCode()).message(exception.getMessage()).build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorDto handleResourceNotFound(ResourceNotFoundException ex) {
        logger.error("Resource not found: {}", ex.getMessage());
        return ApiErrorDto.builder().errorCode(ex.getErrorCode()).message(ex.getMessage()).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorDto handleException(Exception exception) {
        logger.error("General error occurred: ", exception);
        return ApiErrorDto.builder().errorCode("GENERAL").message(exception.getMessage()).build();
    }
}
