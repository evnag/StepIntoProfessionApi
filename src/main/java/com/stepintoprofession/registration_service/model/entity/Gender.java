package com.stepintoprofession.registration_service.model.entity;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    UNKNOWN("UNKNOWN");

    private final String value;

    Gender(String value) {
        this.value = value;
    }
}