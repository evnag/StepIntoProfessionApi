package com.stepintoprofession.registration_service.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    BAD_REQUEST_ERROR(400),
    UNAUTHORISED_ERROR(401),
    FORBIDDEN_ERROR(403),
    NOT_FOUND_ERROR(404);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

}
