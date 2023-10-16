package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.InternDto;
import com.stepintoprofession.registration_service.model.entity.Gender;
import com.stepintoprofession.registration_service.model.entity.InternEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InternMapper {

    InternMapper INSTANCE = Mappers.getMapper(InternMapper.class);

    @Mapping(target = "address", source = "address.id")
    @Mapping(target = "gender", expression = "java(getValue(intern.getGender()))")
    @Mapping(target = "lastName", expression = "java(fullNameToDto(intern).getLastName())")
    @Mapping(target = "firstName", expression = "java(fullNameToDto(intern).getFirstName())")
    @Mapping(target = "middleName", expression = "java(fullNameToDto(intern).getMiddleName())")
    InternDto entityToDto(InternEntity intern);

    @Mapping(target = "fullName", source = ".")
    @Mapping(target = "address.id", source = "address")
    @Mapping(target = "gender", source = "gender")
    InternEntity dtoToEntity(InternDto internDto);

    default String getValue(Gender gender) {
        return gender.name();
    }

    default String mapToFullName(InternDto internDto) {
        return internDto.getLastName() + " " + internDto.getFirstName() + " " + internDto.getMiddleName();
    }

    default InternDto fullNameToDto(InternEntity intern) {
        InternDto internDto = new InternDto();
        internDto.setLastName(intern.getFullName().split(" (?!.* )")[0]);
        internDto.setFirstName(intern.getFullName().split(" (?!.* )")[1]);
        internDto.setMiddleName(intern.getFullName().split(" (?!.* )")[2]);
        return internDto;
    }
}
