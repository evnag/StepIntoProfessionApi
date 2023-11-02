package com.stepintoprofession.registration_service.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Validated
public class RecruiterDto extends ParticipantsDto {

    @NotNull
    private String intern;
    @NotNull
    private String company;
}
