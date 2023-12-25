package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.ProjectSeasonDto;
import com.stepintoprofession.registration_service.model.entity.ProjectSeason;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProjectSeasonMapperTest {

    private final ProjectSeasonMapper mapper = Mappers.getMapper(ProjectSeasonMapper.class);

    @Test
    public void entityToDto_thenOk() {

        ProjectSeason entity = generateSeason();

        ProjectSeasonDto dto = mapper.entityToDto(entity);

        assertEquals(dto.getStartDate(), "2023-09-10");
        assertEquals(dto.getSeasonNumber(), 15);
    }

    @Test
    public void dtoToEntity_thenOk() {

        ProjectSeasonDto dto = new ProjectSeasonDto();
        dto.setStartDate("2023-09-10");
        dto.setSeasonNumber(15);

        ProjectSeason entity = mapper.dtoToEntity(dto);

        assertEquals(entity.getStartDate(), LocalDate.of(2023, 9, 10));
        assertEquals(entity.getSeasonNumber(), 15);
        assertNull(entity.getEndDate());
    }

    @Test
    public void listToListDto_thenOk() {

        List<ProjectSeason> entityList = List.of(generateSeason());

        List<ProjectSeasonDto> dtoList = mapper.listToListDto(entityList);

        assertThat(dtoList).hasSize(1);
        assertThat(dtoList.get(0).getSeasonNumber()).isEqualTo(15);
    }

    @Test
    public void updateEntityFromDto_thenOk() {

        ProjectSeason entity = generateSeason();

        ProjectSeasonDto dto = new ProjectSeasonDto();
        dto.setStartDate("2020-01-02");
        dto.setSeasonNumber(10);

        mapper.updateEntityFromDto(dto, entity);

        assertEquals(entity.getStartDate(), LocalDate.of(2020, 1, 2));
        assertEquals(entity.getSeasonNumber(), 10);
    }

    private ProjectSeason generateSeason() {

        ProjectSeason entity = new ProjectSeason();
        entity.setSeasonNumber(15);
        entity.setStartDate(LocalDate.of(2023, 9, 10));

        return entity;
    }
}
