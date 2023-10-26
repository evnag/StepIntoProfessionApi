package com.stepintoprofession.registration_service.mapper;

import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.Period;

public interface BaseMapper {

    @Named("calculateAge")
    default Integer calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }


}
