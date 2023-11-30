package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.TeamDto;
import com.stepintoprofession.registration_service.model.entity.TeamEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    ProjectSeasonMapper INSTANCE = Mappers.getMapper(ProjectSeasonMapper.class);

    @Mapping(target = "internFullName", source = "intern.fullName")
    @Mapping(target = "mentorFullName", source = "mentor.fullName")
    @Mapping(target = "recruiterFullName", source = "recruiter.fullName")
    @Mapping(target = "season", source = "season.seasonNumber")
    TeamDto entityToDto(TeamEntity entity);

    @Mapping(target = "intern.id", source = "internFullName")
    @Mapping(target = "mentor.id", source = "mentorFullName")
    @Mapping(target = "recruiter.id", source = "recruiterFullName")
    @Mapping(target = "season.seasonNumber", source = "season")
    TeamEntity dtoToEntity(TeamDto dto);
}
