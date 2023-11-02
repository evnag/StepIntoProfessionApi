package com.stepintoprofession.registration_service.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Validated
public class InternDto extends ParticipantsDto {

    @NotNull
    private String disabilityGroup;
    @NotNull
    private String disabilityType;
    private List<String> languageSkill;
    private String cvPath;
    private String videoCvPath;
    private String tildaCvPath;
}
