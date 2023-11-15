package com.stepintoprofession.registration_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class AddressDto {

    @NotNull
    private UUID id;
    @NotBlank
    private String country;
    @NotBlank
    private String zip;
    @NotBlank
    private String city;
    @NotBlank
    private String region;
    @NotBlank
    private String street;
    @NotBlank
    private String building;
    @NotBlank
    private String apartment;
}
