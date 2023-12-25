package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.RecruiterDto;
import com.stepintoprofession.registration_service.model.entity.Participants.RecruiterEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RecruiterMapper extends BaseMapper {

    @IterableMapping(qualifiedByName = "entityToDto")
    public abstract List<RecruiterDto> listToListDto(List<RecruiterEntity> recruiters);

    @Mapping(target = "gender", expression = "java(entity.getGender().name())")
    @Mapping(target = "age", source = "birthday", qualifiedByName = "calculateAge")
    @Mapping(target = "seasonNumber", source = "projects", qualifiedByName = "projectListToListOfSeasonNumbers")
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "middleName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "intern", source = "intern.id")
    @Named(value = "entityToDto")
    public abstract RecruiterDto entityToDto(RecruiterEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", source = ".")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "projects", source = "seasonNumber", qualifiedByName = "seasonNumbersToProjectSeasons")
    @Mapping(target = "intern.id", source = "intern")
    public abstract RecruiterEntity dtoToEntity(RecruiterDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", source = ".")
    @Mapping(target = "projects", source = "seasonNumber", qualifiedByName = "seasonNumbersToProjectSeasons")
    @Mapping(target = "intern.id", source = "intern")
    public abstract void updateEntityFromDto(RecruiterDto dto, @MappingTarget RecruiterEntity entity);
}
