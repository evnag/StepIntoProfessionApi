package com.stepintoprofession.registration_service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INTERN_NOT_FOUND_ERROR(10, "Intern not found"),
    MENTOR_NOT_FOUND_ERROR(11, "Mentor not found"),
    RECRUITER_NOT_FOUND_ERROR(12, "Recruiter not found"),
    SEASON_NOT_FOUND_ERROR(13, "Season not found"),
    NO_MATCHES_FOUND_ERROR(2, "No matches found");

    private final int code;
    private final String desc;

    public String getErrorMessage(String details) {
        return String.format("%s: %s", this.desc, details);
    }

}
