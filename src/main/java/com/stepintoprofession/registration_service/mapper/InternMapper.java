package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.InternDto;
import com.stepintoprofession.registration_service.model.entity.Participants.InternEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class InternMapper extends BaseMapper {

    @IterableMapping(qualifiedByName = "entityToDto")
    public abstract List<InternDto> listToListDto(List<InternEntity> interns);

    @Mapping(target = "gender", expression = "java(entity.getGender().name())")
    @Mapping(target = "age", source = "birthday", qualifiedByName = "calculateAge")
    @Mapping(target = "seasonNumber", source = "projectId", qualifiedByName = "projectListToListOfSeasonNumbers")
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "middleName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Named(value = "entityToDto")
    public abstract InternDto entityToDto(InternEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", source = ".")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "projectId", source = "seasonNumber", qualifiedByName = "seasonNumbersToProjectSeasons")
    public abstract InternEntity dtoToEntity(InternDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "fullName", source = ".")
    public abstract void updateEntityFromDto(InternDto dto, @MappingTarget InternEntity entity);
}
