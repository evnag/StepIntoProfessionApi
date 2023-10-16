package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.InternDto;
import com.stepintoprofession.registration_service.model.entity.Gender;
import com.stepintoprofession.registration_service.model.entity.InternEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InternMapper {

    InternMapper INSTANCE = Mappers.getMapper(InternMapper.class);

    @IterableMapping(qualifiedByName = "entityToDto")
    List<InternDto> listToListDto(List<InternEntity> interns);

    @Mapping(target = "address", source = "address.id")
    @Mapping(target = "gender", expression = "java(getValue(intern.getGender()))")
    @Mapping(target = "lastName", expression = "java(fullNameToDto(intern).getLastName())")
    @Mapping(target = "firstName", expression = "java(fullNameToDto(intern).getFirstName())")
    @Mapping(target = "middleName", expression = "java(fullNameToDto(intern).getMiddleName())")
    @Named(value = "entityToDto")
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
        int index = intern.getFullName().lastIndexOf(' ');
        InternDto internDto = new InternDto();

        internDto.setLastName(intern.getFullName().substring(0, index));
        internDto.setFirstName(intern.getFullName().substring(index + 1));
        internDto.setMiddleName(intern.getFullName().substring(index + 2));
        return internDto;
    }

}
