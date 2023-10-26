package com.stepintoprofession.registration_service.model.dto;

import lombok.*;

import java.util.List;

@Data
public class MentorDto {

    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String gender;
    private String birthday;
    private String internship;
    private String address;
    private String intern;
    private List<String> seasonNumber;
}
