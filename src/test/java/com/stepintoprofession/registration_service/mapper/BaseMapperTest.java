package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.InternDto;
import com.stepintoprofession.registration_service.model.dto.ParticipantsDto;
import com.stepintoprofession.registration_service.model.entity.BaseEntity;
import com.stepintoprofession.registration_service.model.entity.ProjectSeason;
import com.stepintoprofession.registration_service.repository.ProjectSeasonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {
        InternMapperImpl.class,
        MentorMapperImpl.class,
        RecruiterMapperImpl.class
})
public class BaseMapperTest {

    private final BaseMapper baseMapper = Mockito.mock(BaseMapper.class, Mockito.CALLS_REAL_METHODS);

    @InjectMocks
    private InternMapperImpl internMapperTest;
    @InjectMocks
    private MentorMapperImpl mentorMapperTest;
    @InjectMocks
    private RecruiterMapperImpl recruiterMapperTest;

    @Mock
    private ProjectSeasonRepository seasonRepository;

    @Test
    public void calculateAge_thenOk() {

        LocalDate birthDate = LocalDate.of(2022, 10, 30);
        assertThat(baseMapper.calculateAge(birthDate)).isEqualTo(1);
    }

    @Test
    public void projectListToListOfSeasonNumbers_thenOk() {

        ProjectSeason projectSeason = new ProjectSeason(15, LocalDate.of(2022, 10, 30));

        List<String> result = baseMapper.projectListToListOfSeasonNumbers(Collections.singletonList(projectSeason));

        assertThat(result).hasSize(1);
        assertThat(result.get(0).contains("15")).isTrue();
    }

    @Test
    public void seasonNumbersToProjectSeasons_thenOk() {

        List<String> seasonNumbers = Arrays.asList("14", "15");
        ProjectSeason projectSeason = new ProjectSeason(14, LocalDate.of(2022, 10, 30));
        ProjectSeason projectSeason2 = new ProjectSeason(15, LocalDate.of(2023, 9, 1));
        projectSeason.setId(UUID.randomUUID());
        projectSeason2.setId(UUID.randomUUID());

        InternDto dto = generateDto();
        dto.setSeasonNumber(seasonNumbers);
        when(seasonRepository.findProjectBySeasonNumber(14)).thenReturn(projectSeason);
        when(seasonRepository.findProjectBySeasonNumber(15)).thenReturn(projectSeason2);
        List<ProjectSeason> result = internMapperTest.dtoToEntity(dto).getProjectId();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getSeasonNumber()).isEqualTo(14);
        assertThat(result.get(0).getStartDate()).isEqualTo(LocalDate.of(2022, 10, 30));
        assertThat(result.get(1).getSeasonNumber()).isEqualTo(15);
        assertThat(result.get(1).getStartDate()).isEqualTo(LocalDate.of(2023, 9, 1));

    }

    @Test
    public void fullNameToDto_ThenExceptionThrows() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            baseMapper.fullNameToDto(null, null);
        });

        String expectedMessage = "Parameters must not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void fullNameToDto_ThenExceptionThrowsAgain() {

        Exception exception = assertThrows(RuntimeException.class, () -> {
            baseMapper.fullNameToDto(new BaseEntity(), ParticipantsDto.builder());
        });

        String expectedMessage = "Method entity.getFullName() returned the null value";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
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
