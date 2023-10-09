package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.dto.InternDto;
import com.stepintoprofession.registration_service.model.Intern;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InternMapper {

    InternMapper INSTANCE = Mappers.getMapper(InternMapper.class);

    @Mapping(target = "address", source = "address.id")
    InternDto entityToDto(Intern intern);

    @Mapping(target = "address.id",source = "address")
    Intern dtoToEntity(InternDto internDto);
}
