package com.univ.billingbaselite.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class CMBusinessException extends RuntimeException {

    private final HttpStatus statusCode;

    public CMBusinessException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public CMBusinessException(String message) {
        super(message);
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
