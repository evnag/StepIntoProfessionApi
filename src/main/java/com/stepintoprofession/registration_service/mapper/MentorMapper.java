package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.MentorDto;
import com.stepintoprofession.registration_service.model.entity.MentorEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MentorMapper extends BaseMapper {

    @IterableMapping(qualifiedByName = "entityToDto")
    public abstract List<MentorDto> listToListDto(List<MentorEntity> mentors);

    @Mapping(target = "gender", expression = "java(entity.getGender().name())")
    @Mapping(target = "age", source = "birthday", qualifiedByName = "calculateAge")
    @Mapping(target = "seasonNumber", source = "projects", qualifiedByName = "projectListToListOfSeasonNumbers")
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "middleName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "intern", source = "intern.id")
    @Named(value = "entityToDto")
    public abstract MentorDto entityToDto(MentorEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", source = ".")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "projects", source = "seasonNumber", qualifiedByName = "seasonNumbersToProjectSeasons")
    @Mapping(target = "intern.id", source = "intern")
    public abstract MentorEntity dtoToEntity(MentorDto dto);

    String mapToFullName(MentorDto dto) {
        if (dto == null) {
            return null;
        }
        return dto.getLastName() + " " + dto.getFirstName() + " " + dto.getMiddleName();
    }

    @AfterMapping
    void fullNameToDto(MentorEntity entity, @MappingTarget MentorDto dto) {
        if (entity == null || dto == null) {
            throw new RuntimeException("Parameters must not be null");
        }
        if (entity.getFullName() != null) {
            String[] partsOfName = entity.getFullName().split("\\s");
            dto.setLastName(partsOfName[0]);
            dto.setFirstName(partsOfName[1]);
            if (partsOfName.length > 2) {
                dto.setMiddleName(partsOfName[2]);
            } else {
                dto.setMiddleName("");
            }
        } else {
            throw new RuntimeException("Method entity.getFullName() returned the null value");
        }

    }
}
