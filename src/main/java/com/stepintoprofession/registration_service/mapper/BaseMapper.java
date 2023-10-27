package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.entity.ProjectSeason;
import com.stepintoprofession.registration_service.repository.ProjectSeasonRepository;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseMapper {

    @Autowired
    private ProjectSeasonRepository seasonRepository;

    @Named("calculateAge")
    Integer calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Named(value = "projectListToListOfSeasonNumbers")
    List<String> projectListToListOfSeasonNumbers(List<ProjectSeason> projectSeasons) {
        return projectSeasons.stream()
                .map(p -> p.getSeasonNumber().toString())
                .collect(Collectors.toList());
    }

    @Named(value = "seasonNumbersToProjectSeasons")
    List<ProjectSeason> seasonNumbersToProjectSeasons(List<String> seasonNumbers) {
        return seasonNumbers.stream()
                .mapToInt(Integer::parseInt)
                .mapToObj(seasonRepository::findProjectBySeasonNumber)
                .collect(Collectors.toList());
    }
}