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
public class TeamDto {

    @NotNull
    private UUID id;
    @NotBlank
    private String internFullName;
    @NotBlank
    private String mentorFullName;
    @NotBlank
    private String recruiterFullName;
    @NotBlank
    private String internship;
    @NotNull
    private Integer season;
}
