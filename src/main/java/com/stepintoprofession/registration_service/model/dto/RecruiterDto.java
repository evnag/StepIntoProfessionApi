package com.stepintoprofession.registration_service.model.dto;

import com.stepintoprofession.registration_service.model.entity.Address;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.List;

@Data
@Validated
public class RecruiterDto {

    @NotNull
    private String firstName;
    @NotNull
    private String middleName;
    @NotNull
    private String lastName;
    @Past
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
    @NotNull
    private String intern;
    @NotNull
    private String age;
    @NotNull
    private String company;
    @NotEmpty(message = "Season list cannot be empty.")
    private List<String> seasonNumber;
}
