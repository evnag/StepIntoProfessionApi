package com.stepintoprofession.registration_service.model.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
public class MentorDto {

    private UUID id;
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
    private Integer age;
    private String company;
    private List<String> seasonNumber;
}