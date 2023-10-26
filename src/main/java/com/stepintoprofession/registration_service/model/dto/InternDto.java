package com.stepintoprofession.registration_service.model.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class InternDto {

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
    private Integer age;

    private String disabilityGroup;
    private String disabilityType;
    private List<String> languageSkill;

    private String cvPath;
    private String videoCvPath;
    private String tildaCvPath;
    private List<String> seasonNumber;
}
