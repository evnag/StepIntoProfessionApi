package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.InternDto;
import com.stepintoprofession.registration_service.model.entity.InternEntity;
import com.stepintoprofession.registration_service.model.entity.ProjectSeason;
import com.stepintoprofession.registration_service.repository.ProjectSeasonRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class InternMapper implements BaseMapper {

//    InternMapper INSTANCE = Mappers.getMapper(InternMapper.class);

    @Autowired
    private ProjectSeasonRepository seasonRepository;

    @IterableMapping(qualifiedByName = "entityToDto")
    public abstract List<InternDto> listToListDto(List<InternEntity> interns);

    @Mapping(target = "address", source = "address.id")
    @Mapping(target = "gender", expression = "java(intern.getGender().name())")
    @Mapping(target = "age", source = "birthday", qualifiedByName = "calculateAge")
    @Mapping(target = "seasonNumber", source = "projectId", qualifiedByName = "projectListToListOfSeasonNumbers")
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "middleName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Named(value = "entityToDto")
    public abstract InternDto entityToDto(InternEntity intern);

    @Mapping(target = "fullName", source = ".")
    @Mapping(target = "address.id", source = "address")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "projectId", source = "seasonNumber", qualifiedByName = "seasonNumbersToProjectSeasons")
    public abstract InternEntity dtoToEntity(InternDto internDto);

    String mapToFullName(InternDto internDto) {
        return internDto.getLastName() + " " + internDto.getFirstName() + " " + internDto.getMiddleName();
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

    @AfterMapping
    void fullNameToDto(InternEntity intern, @MappingTarget InternDto dto) {
        String[] partsOfName = intern.getFullName().split("\\s");
        dto.setLastName(partsOfName[0]);
        dto.setFirstName(partsOfName[1]);
        if (partsOfName.length > 2) {
            dto.setMiddleName(partsOfName[2]);
        } else {
            dto.setMiddleName("");
        }
    }

}
