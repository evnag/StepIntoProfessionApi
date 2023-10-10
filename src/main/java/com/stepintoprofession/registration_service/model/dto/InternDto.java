package com.stepintoprofession.registration_service.model.dto;

import lombok.Data;

@Data
public class InternDto {

    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String gender;
    private String birthday;
    private String internship;
    private Integer address;
}
