package com.stepintoprofession.registration_service.mapper;

import com.stepintoprofession.registration_service.exception.ErrorCode;
import com.stepintoprofession.registration_service.exception.RegistrationServiceException;
import com.stepintoprofession.registration_service.model.dto.TeamDto;
import com.stepintoprofession.registration_service.model.entity.Participants.InternEntity;
import com.stepintoprofession.registration_service.model.entity.Participants.MentorEntity;
import com.stepintoprofession.registration_service.model.entity.Participants.RecruiterEntity;
import com.stepintoprofession.registration_service.model.entity.TeamEntity;
import com.stepintoprofession.registration_service.repository.InternRepository;
import com.stepintoprofession.registration_service.repository.MentorRepository;
import com.stepintoprofession.registration_service.repository.RecruiterRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TeamMapper {

    @Autowired
    private InternRepository internRepository;
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private RecruiterRepository recruiterRepository;

    @Mapping(target = "internFullName", source = "intern.fullName")
    @Mapping(target = "mentorFullName", source = "mentor.fullName")
    @Mapping(target = "recruiterFullName", source = "recruiter.fullName")
    @Mapping(target = "season", source = "season.seasonNumber")
    public abstract TeamDto entityToDto(TeamEntity entity);

    @Mapping(target = "intern", source = "internFullName", qualifiedByName = "internFullNameToInternEntity")
    @Mapping(target = "mentor", source = "mentorFullName", qualifiedByName = "mentorFullNameToMentorEntity")
    @Mapping(target = "recruiter", source = "recruiterFullName", qualifiedByName = "recruiterFullNameToRecruiterEntity")
    @Mapping(target = "season.seasonNumber", source = "season")
    public abstract TeamEntity dtoToEntity(TeamDto dto);

    @Named(value = "internFullNameToInternEntity")
    InternEntity internFullNameToInternEntity(String internFullName) {
        if (internFullName == null || internFullName.isBlank()) {
            return null;
        }
        return internRepository.findInternByFullNameLike(internFullName)
                .orElseThrow(() -> new RegistrationServiceException(ErrorCode.INTERN_NOT_FOUND_ERROR
                        .getErrorMessage(internFullName), ErrorCode.INTERN_NOT_FOUND_ERROR));
    }

    @Named(value = "mentorFullNameToMentorEntity")
    MentorEntity mentorFullNameToMentorEntity(String mentorFullName) {
        if (mentorFullName == null || mentorFullName.isBlank()) {
            return null;
        }
        return mentorRepository.findMentorByFullNameLike(mentorFullName)
                .orElseThrow(() -> new RegistrationServiceException(ErrorCode.MENTOR_NOT_FOUND_ERROR
                        .getErrorMessage(mentorFullName), ErrorCode.MENTOR_NOT_FOUND_ERROR));
    }

    @Named(value = "recruiterFullNameToRecruiterEntity")
    RecruiterEntity recruiterFullNameToRecruiterEntity(String recruiterFullName) {
        if (recruiterFullName == null || recruiterFullName.isBlank()) {
            return null;
        }
        return recruiterRepository.findRecruiterByFullNameLike(recruiterFullName)
                .orElseThrow(() -> new RegistrationServiceException(ErrorCode.RECRUITER_NOT_FOUND_ERROR
                        .getErrorMessage(recruiterFullName), ErrorCode.RECRUITER_NOT_FOUND_ERROR));
    }
}
