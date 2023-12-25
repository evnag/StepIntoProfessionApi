package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.model.dto.TeamDto;
import com.stepintoprofession.registration_service.model.entity.Address;
import com.stepintoprofession.registration_service.model.entity.Gender;
import com.stepintoprofession.registration_service.model.entity.Participants.InternEntity;
import com.stepintoprofession.registration_service.model.entity.Participants.MentorEntity;
import com.stepintoprofession.registration_service.model.entity.Participants.RecruiterEntity;
import com.stepintoprofession.registration_service.model.entity.ProjectSeason;
import com.stepintoprofession.registration_service.model.entity.TeamEntity;
import com.stepintoprofession.registration_service.repository.InternRepository;
import com.stepintoprofession.registration_service.repository.MentorRepository;
import com.stepintoprofession.registration_service.repository.RecruiterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {
        MentorMapperImpl.class
})
public class TeamMapperTest {

    private final TeamMapper mapper = Mappers.getMapper(TeamMapper.class);
    @Mock
    private InternRepository internRepository;
    @Mock
    private MentorRepository mentorRepository;
    @Mock
    private RecruiterRepository recruiterRepository;
    @InjectMocks
    private TeamMapperImpl teamMapper;


    @Test
    public void entityToDto_thenOk() {

        ProjectSeason season = new ProjectSeason();
        season.setSeasonNumber(15);
        season.setStartDate(LocalDate.of(2023, 9, 10));

        TeamEntity entity = new TeamEntity();
        entity.setId(UUID.randomUUID());
        entity.setIntern(generateIntern());
        entity.setMentor(generateMentor());
        entity.setRecruiter(generateRecruiter());
        entity.setInternship("IT");
        entity.setSeason(season);

        TeamDto dto = mapper.entityToDto(entity);

        assertEquals(dto.getId().getClass(), UUID.class);
        assertEquals(dto.getInternFullName(), entity.getIntern().getFullName());
        assertEquals(dto.getMentorFullName(), entity.getMentor().getFullName());
        assertEquals(dto.getRecruiterFullName(), entity.getRecruiter().getFullName());
        assertEquals(dto.getInternship(), "IT");
        assertEquals(dto.getSeason(), 15);
    }

    @Test
    public void dtoToEntity_thenOk() {

        TeamDto dto = new TeamDto();
        dto.setId(UUID.randomUUID());
        dto.setInternFullName("Intern Full Name");
        dto.setMentorFullName("Mentor Full Name");
        dto.setRecruiterFullName("Recruiter Full Name");
        dto.setInternship("IT");
        dto.setSeason(15);

        when(internRepository.findInternByFullNameLike(dto.getInternFullName())).thenReturn(Optional.of(generateIntern()));
        when(mentorRepository.findMentorByFullNameLike(dto.getMentorFullName())).thenReturn(Optional.of(generateMentor()));
        when(recruiterRepository.findRecruiterByFullNameLike(dto.getRecruiterFullName())).thenReturn(Optional.of(generateRecruiter()));

        TeamEntity entity = teamMapper.dtoToEntity(dto);

        assertEquals(entity.getId().getClass(), UUID.class);
        assertEquals(entity.getIntern().getFullName(), dto.getInternFullName());
        assertEquals(entity.getMentor().getFullName(), dto.getMentorFullName());
        assertEquals(entity.getRecruiter().getFullName(), dto.getRecruiterFullName());
        assertEquals(entity.getInternship(), "IT");
        assertEquals(entity.getSeason().getSeasonNumber(), 15);
    }

    private InternEntity generateIntern() {

        ProjectSeason season = new ProjectSeason(15, LocalDate.of(2023, 9, 10));

        InternEntity entity = new InternEntity();
        entity.setFullName("Intern Full Name");
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

    private MentorEntity generateMentor() {

        MentorEntity entity = new MentorEntity();
        entity.setFullName("Mentor Full Name");
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

    private RecruiterEntity generateRecruiter() {

        RecruiterEntity entity = new RecruiterEntity();
        entity.setFullName("Recruiter Full Name");
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
