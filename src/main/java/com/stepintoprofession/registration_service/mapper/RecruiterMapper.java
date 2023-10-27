package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.RecruiterDto;
import com.stepintoprofession.registration_service.model.entity.RecruiterEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RecruiterMapper extends BaseMapper {

    @IterableMapping(qualifiedByName = "entityToDto")
    public abstract List<RecruiterDto> listToListDto(List<RecruiterEntity> recruiters);

    @Mapping(target = "address", source = "address.id")
    @Mapping(target = "gender", expression = "java(recruiter.getGender().name())")
    @Mapping(target = "age", source = "birthday", qualifiedByName = "calculateAge")
    @Mapping(target = "seasonNumber", source = "projects", qualifiedByName = "projectListToListOfSeasonNumbers")
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "middleName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "intern", source = "intern.id")
    @Named(value = "entityToDto")
    public abstract RecruiterDto entityToDto(RecruiterEntity recruiter);

    @Mapping(target = "fullName", source = ".")
    @Mapping(target = "address.id", source = "address")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "projects", source = "seasonNumber", qualifiedByName = "seasonNumbersToProjectSeasons")
    @Mapping(target = "intern.id", source = "intern")
    public abstract RecruiterEntity dtoToEntity(RecruiterDto dto);

    String mapToFullName(RecruiterDto dto) {
        return dto.getLastName() + " " + dto.getFirstName() + " " + dto.getMiddleName();
    }

    @AfterMapping
    void fullNameToDto(RecruiterEntity recruiter, @MappingTarget RecruiterDto dto) {
        String[] partsOfName = recruiter.getFullName().split("\\s");
        dto.setLastName(partsOfName[0]);
        dto.setFirstName(partsOfName[1]);
        if (partsOfName.length > 2) {
            dto.setMiddleName(partsOfName[2]);
        } else {
            dto.setMiddleName("");
        }
    }
}
