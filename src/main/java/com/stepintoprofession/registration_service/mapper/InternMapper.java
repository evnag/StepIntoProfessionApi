package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.InternDto;
import com.stepintoprofession.registration_service.model.entity.Participants.InternEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

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

}
