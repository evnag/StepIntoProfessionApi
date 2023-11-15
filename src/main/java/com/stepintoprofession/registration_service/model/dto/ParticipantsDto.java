package com.stepintoprofession.registration_service.model.dto;

import com.stepintoprofession.registration_service.validate.DateFormat;
import com.stepintoprofession.registration_service.validate.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ParticipantsDto {

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
    private String gender;
    @DateFormat(pattern = "yyyy-MM-dd")
    private String birthday;
    private String internship;
    private AddressDto address;
    private Integer age;
    private List<String> seasonNumber;
}
