package com.gft.commercial.exception;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7291240819541730907L;

    private static final String ERROR_CODE = "NOT_FOUND";
    private static final String RESOURCE_NOT_FOUND_MESSAGE = "%s not found.";

    public ResourceNotFoundException(String resource) {
        super(String.format(RESOURCE_NOT_FOUND_MESSAGE, resource));
    }

    public String getErrorCode() {
        return ERROR_CODE;
    }
}
