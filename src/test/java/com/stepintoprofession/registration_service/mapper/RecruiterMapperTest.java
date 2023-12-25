package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.RecruiterDto;
import com.stepintoprofession.registration_service.model.entity.Address;
import com.stepintoprofession.registration_service.model.entity.Gender;
import com.stepintoprofession.registration_service.model.entity.Participants.InternEntity;
import com.stepintoprofession.registration_service.model.entity.Participants.RecruiterEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecruiterMapperTest {

    private final RecruiterMapper mapper = Mappers.getMapper(RecruiterMapper.class);

    @Test
    public void listToListDto_thenOk() {
        List<RecruiterEntity> entityList = List.of(generateEntity());

        List<RecruiterDto> dtoList = mapper.listToListDto(entityList);

        assertThat(dtoList).hasSize(1);
        assertThat(dtoList.get(0).getCompany()).contains("Some company");
    }

    @Test
    public void entityToDto_ThenOk() {

        RecruiterEntity entity = generateEntity();

        RecruiterDto dto = mapper.entityToDto(entity);

        assertEquals(dto.getFirstName(), "First");
        assertEquals(dto.getLastName(), "Last");
        assertEquals(dto.getBirthday(), "2000-11-01");
    }

    @Test
    public void dtoToEntity_thenOk() {

        RecruiterDto dto = new RecruiterDto();
        dto.setCompany("Test company");
        dto.setIntern(UUID.randomUUID().toString());

        RecruiterEntity entity = mapper.dtoToEntity(dto);

        assertEquals(entity.getCompany(), "Test company");
        assertEquals(entity.getIntern().getId().getClass(), UUID.class);
    }

    @Test
    public void updateEntityFromDto_thenOk() {
        RecruiterEntity entity = generateEntity();

        RecruiterDto dto = new RecruiterDto();
        dto.setCompany("Test company");
        dto.setIntern(UUID.randomUUID().toString());


        mapper.updateEntityFromDto(dto, entity);

        assertEquals(entity.getIntern().getId().getClass(), UUID.class);
        assertEquals(entity.getCompany(), "Test company");
    }

    private RecruiterEntity generateEntity() {

        RecruiterEntity entity = new RecruiterEntity();
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
