package com.stepintoprofession.registration_service.model.dto;

import com.stepintoprofession.registration_service.model.entity.Address;
import com.stepintoprofession.registration_service.validate.Phone;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.List;

@Data
@Validated
public class InternDto {

    @NotNull
    private String firstName;
    @NotNull
    private String middleName;
    @NotNull
    private String lastName;
    @Phone
    private String phoneNumber;
    @Email(message = "Email address has invalid format: ${validatedValue}")
    private String email;
    @NotNull
    private String gender;
    @Past
    @NotNull
    private String birthday;
    @NotNull
    private String internship;
    @NotNull
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
