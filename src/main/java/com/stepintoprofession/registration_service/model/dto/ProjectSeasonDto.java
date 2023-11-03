package com.stepintoprofession.registration_service.model.dto;

import com.stepintoprofession.registration_service.validate.DateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ProjectSeasonDto {

    @NotNull
    private Integer seasonNumber;
    @NotNull
    @DateFormat(pattern = "yyyy-MM-dd")
    private String startDate;
}
