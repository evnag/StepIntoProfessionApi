package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.MentorDto;
import com.stepintoprofession.registration_service.model.entity.Address;
import com.stepintoprofession.registration_service.model.entity.Gender;
import com.stepintoprofession.registration_service.model.entity.Participants.InternEntity;
import com.stepintoprofession.registration_service.model.entity.Participants.MentorEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MentorMapperTest {

    private final MentorMapper mapper = Mappers.getMapper(MentorMapper.class);

    @Test
    public void listToListDto_thenOk() {
        List<MentorEntity> entityList = List.of(generateEntity());

        List<MentorDto> dtoList = mapper.listToListDto(entityList);

        assertThat(dtoList).hasSize(1);
        assertThat(dtoList.get(0).getCompany()).contains("Some company");
    }

    @Test
    public void entityToDto_ThenOk() {

        MentorEntity entity = generateEntity();

        MentorDto dto = mapper.entityToDto(entity);

        assertEquals(dto.getFirstName(), "First");
        assertEquals(dto.getLastName(), "Last");
        assertEquals(dto.getBirthday(), "2000-11-01");
    }

    @Test
    public void dtoToEntity_thenOk() {

        MentorDto dto = new MentorDto();
        dto.setCompany("Test company");
        dto.setIntern(UUID.randomUUID().toString());

        MentorEntity entity = mapper.dtoToEntity(dto);

        assertEquals(entity.getCompany(), "Test company");
        assertEquals(entity.getIntern().getId().getClass(), UUID.class);
    }

    @Test
    public void updateEntityFromDto_thenOk() {
        MentorEntity entity = generateEntity();

        MentorDto dto = new MentorDto();
        dto.setCompany("Test company");
        dto.setIntern(UUID.randomUUID().toString());


        mapper.updateEntityFromDto(dto, entity);

        assertEquals(entity.getIntern().getId().getClass(), UUID.class);
        assertEquals(entity.getCompany(), "Test company");
    }

    private MentorEntity generateEntity() {

        MentorEntity entity = new MentorEntity();
        entity.setFullName("Last First Middle");
        entity.setPhoneNumber("+79009990000");
        entity.setEmail("some@mail.com");
        entity.setGender(Gender.UNKNOWN);
        entity.setBirthday(LocalDate.of(2000, 11, 1));
        entity.setAddress(new Address());
        entity.setInternship("Dev");
        entity.setCompany("Some company");
        entity.setIntern(new InternEntity());

        return entity;
    }
}
