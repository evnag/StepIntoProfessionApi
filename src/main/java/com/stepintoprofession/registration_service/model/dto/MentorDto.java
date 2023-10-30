package com.stepintoprofession.registration_service.model.dto;

import com.stepintoprofession.registration_service.model.entity.Address;
import com.stepintoprofession.registration_service.validate.DateFormat;
import com.stepintoprofession.registration_service.validate.Phone;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Validated
public class MentorDto {

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
    @DateFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private String birthday;
    @NotNull
    private String internship;
    @NotNull
    private Address address;
    @NotNull
    private String intern;
    @NotNull
    private Integer age;
    @NotNull
    private String company;
    @NotEmpty(message = "Season list cannot be empty.")
    private List<String> seasonNumber;
}
