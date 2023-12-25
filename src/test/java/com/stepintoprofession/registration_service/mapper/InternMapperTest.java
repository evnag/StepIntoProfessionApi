package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.InternDto;
import com.stepintoprofession.registration_service.model.entity.Address;
import com.stepintoprofession.registration_service.model.entity.Gender;
import com.stepintoprofession.registration_service.model.entity.Participants.InternEntity;
import com.stepintoprofession.registration_service.model.entity.ProjectSeason;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InternMapperTest {

    private final InternMapper internMapper = Mappers.getMapper(InternMapper.class);

    @Test
    public void listToListDto_thenOk() {

        List<InternEntity> entityList = List.of(generateEntity());

        List<InternDto> dtoList = internMapper.listToListDto(entityList);

        assertThat(dtoList).hasSize(1);
        assertThat(dtoList.get(0).getDisabilityGroup()).contains("1");
    }

    @Test
    public void entityToDto_ThenOk() {

        InternEntity entity = generateEntity();

        InternDto dto = internMapper.entityToDto(entity);

        assertEquals(dto.getFirstName(), "First");
        assertEquals(dto.getLastName(), "Last");
        assertEquals(dto.getBirthday(), "2000-11-01");
    }

    @Test
    public void dtoToEntity_thenOk() {

        InternDto dto = generateDto();

        InternEntity entity = internMapper.dtoToEntity(dto);

        assertEquals(entity.getDisabilityGroup(), 1);
        assertEquals(entity.getDisabilityType(), "PODA");
        assertEquals(entity.getLanguageSkill().get(0), "eng");
        assertEquals(entity.getCvPath(), "some/path");
    }

    @Test
    public void updateEntityFromDto_thenOk() {

        InternEntity entity = generateEntity();
        InternDto dto = new InternDto();

        dto.setDisabilityGroup("2");
        dto.setDisabilityType("Test");
        dto.setLanguageSkill(new ArrayList<>(List.of("rus")));
        dto.setCvPath("some/test");
        dto.setVideoCvPath("some/videoPath");
        dto.setTildaCvPath("some/tildaPath");

        internMapper.updateEntityFromDto(dto, entity);

        assertEquals(entity.getDisabilityGroup(), 2);
        assertEquals(entity.getDisabilityType(), "Test");
        assertEquals(entity.getLanguageSkill().get(0), "rus");
        assertEquals(entity.getCvPath(), "some/test");
    }

    private InternEntity generateEntity() {

        ProjectSeason season = new ProjectSeason(15, LocalDate.of(2023, 9, 10));

        InternEntity entity = new InternEntity();
        entity.setFullName("Last First Middle");
        entity.setPhoneNumber("+79009990000");
        entity.setEmail("some@mail.com");
        entity.setGender(Gender.UNKNOWN);
        entity.setBirthday(LocalDate.of(2000, 11, 1));
        entity.setAddress(new Address());
        entity.setInternship("Dev");
        entity.setDisabilityGroup(1);
        entity.setDisabilityType("PODA");
        entity.setLanguageSkill(new ArrayList<>(List.of("eng")));
        entity.setCvPath("some/path");
        entity.setVideoCvPath("some/videoPath");
        entity.setTildaCvPath("some/tildaPath");
        entity.setProjectId(List.of(season));

        return entity;
    }

    private InternDto generateDto() {

        InternDto dto = new InternDto();

        dto.setDisabilityGroup("1");
        dto.setDisabilityType("PODA");
        dto.setLanguageSkill(new ArrayList<>(List.of("eng")));
        dto.setCvPath("some/path");
        dto.setVideoCvPath("some/videoPath");
        dto.setTildaCvPath("some/tildaPath");

        return dto;
    }
}
