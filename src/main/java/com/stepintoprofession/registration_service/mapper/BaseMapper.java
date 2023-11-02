package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.ParticipantsDto;
import com.stepintoprofession.registration_service.model.entity.BaseEntity;
import com.stepintoprofession.registration_service.model.entity.ProjectSeason;
import com.stepintoprofession.registration_service.repository.ProjectSeasonRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
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
        if (projectSeasons == null) {
            return null;
        }
        return projectSeasons.stream()
                .map(p -> p.getSeasonNumber().toString())
                .collect(Collectors.toList());
    }

    @Named(value = "seasonNumbersToProjectSeasons")
    List<ProjectSeason> seasonNumbersToProjectSeasons(List<String> seasonNumbers) {
        if (seasonNumbers == null) {
            return null;
        }
        return seasonNumbers.stream()
                .mapToInt(Integer::parseInt)
                .mapToObj(seasonRepository::findProjectBySeasonNumber)
                .collect(Collectors.toList());
    }

    <T extends ParticipantsDto> String mapToFullName(T dto) {
        if (dto == null) {
            return null;
        }
        return dto.getLastName() + " " + dto.getFirstName() + " " + dto.getMiddleName();
    }

    @AfterMapping
    <T extends BaseEntity, V extends ParticipantsDto> void fullNameToDto(T entity, @MappingTarget V.ParticipantsDtoBuilder<?, ?> dto) {
        if (entity == null || dto == null) {
            throw new RuntimeException("Parameters must not be null");
        }
        if (entity.getFullName() != null) {
            String[] partsOfName = entity.getFullName().split("\\s");
            dto.lastName(partsOfName[0]);
            dto.firstName(partsOfName[1]);
            if (partsOfName.length > 2) {
                dto.middleName(partsOfName[2]);
            } else {
                dto.middleName("");
            }
        } else {
            throw new RuntimeException("Method entity.getFullName() returned the null value");
        }
    }
}
