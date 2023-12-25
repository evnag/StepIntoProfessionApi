package com.stepintoprofession.registration_service.exception;

import lombok.Getter;

@Getter
public class RegistrationServiceException extends RuntimeException {

    private Integer errorCode;

    public RegistrationServiceException(String message) {
        super(message);
    }

    public RegistrationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationServiceException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode.getCode();
    }

    public RegistrationServiceException(String message, Throwable cause, Integer errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

}
