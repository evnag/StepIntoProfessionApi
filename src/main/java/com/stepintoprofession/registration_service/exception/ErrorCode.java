package com.stepintoprofession.registration_service.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    NOT_FOUND_ERROR(1),
    NO_MATCHES_FOUND_ERROR(2);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

}
