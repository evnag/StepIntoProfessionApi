package com.stepintoprofession.registration_service.model.dto;

import com.stepintoprofession.registration_service.model.entity.Address;
import lombok.Data;

import java.util.List;

@Data
public class InternDto {

    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String gender;
    private String birthday;
    private String internship;
    private Address address;
    private Integer age;

    private String disabilityGroup;
    private String disabilityType;
    private List<String> languageSkill;

    private String cvPath;
    private String videoCvPath;
    private String tildaCvPath;
    private List<String> seasonNumber;
}
