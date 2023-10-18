package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.InternDto;
import com.stepintoprofession.registration_service.model.entity.Gender;
import com.stepintoprofession.registration_service.model.entity.InternEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.Period;
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
//    @Mapping(target = "birthday", source = "intern.birthday", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "age", expression = "java(calculateAge(intern.getBirthday()))")
    @Mapping(target = "seasonNumber", source = "projectId.seasonNumber")
    @Named(value = "entityToDto")
    InternDto entityToDto(InternEntity intern);

    @Mapping(target = "fullName", source = ".")
    @Mapping(target = "address.id", source = "address")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "projectId.seasonNumber", source = "seasonNumber")
    InternEntity dtoToEntity(InternDto internDto);

    default String getValue(Gender gender) {
        return gender.name();
    }

    default String mapToFullName(InternDto internDto) {
        return internDto.getLastName() + " " + internDto.getFirstName() + " " + internDto.getMiddleName();
    }

    default InternDto fullNameToDto(InternEntity intern) {
        String[] partsOfName = intern.getFullName().split("\\s");
        InternDto internDto = new InternDto();

        internDto.setLastName(partsOfName[0]);
        internDto.setFirstName(partsOfName[1]);
        if (partsOfName.length > 2) {
            internDto.setMiddleName(partsOfName[2]);
        } else {
            internDto.setMiddleName("");
        }
        return internDto;
    }

    default Integer calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

}
