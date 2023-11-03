package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.ProjectSeasonDto;
import com.stepintoprofession.registration_service.model.entity.ProjectSeason;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectSeasonMapper {

    ProjectSeasonMapper INSTANCE = Mappers.getMapper(ProjectSeasonMapper.class);

    ProjectSeasonDto entityToDto(ProjectSeason entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startDate", source = "dto.startDate", dateFormat = "yyyy-MM-dd")
    ProjectSeason dtoToEntity(ProjectSeasonDto dto);

    List<ProjectSeasonDto> listToListDto(List<ProjectSeason> seasons);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(ProjectSeasonDto dto, @MappingTarget ProjectSeason entity);

}
