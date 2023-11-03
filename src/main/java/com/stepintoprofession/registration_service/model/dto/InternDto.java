package com.stepintoprofession.registration_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class InternDto extends ParticipantsDto {

    private String disabilityGroup;
    private String disabilityType;
    private List<String> languageSkill;
    private String cvPath;
    private String videoCvPath;
    private String tildaCvPath;
}
