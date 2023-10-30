package com.stepintoprofession.registration_service.service;

import com.stepintoprofession.registration_service.exception.ErrorCode;
import com.stepintoprofession.registration_service.exception.RegistrationServiceException;
import com.stepintoprofession.registration_service.mapper.MentorMapper;
import com.stepintoprofession.registration_service.model.dto.MentorDto;
import com.stepintoprofession.registration_service.model.entity.MentorEntity;
import com.stepintoprofession.registration_service.repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MentorService {

    private final MentorRepository mentorRepository;
    private final MentorMapper mapper;

    public MentorDto save(MentorDto dto) {
        if (dto == null) {
            return null;
        }
        return mapper.entityToDto(mentorRepository.save(mapper.dtoToEntity(dto)));
    }

    public List<MentorDto> findALL() {
        return mapper.listToListDto(mentorRepository.findAll());
    }

    public ResponseEntity<Void> delete(UUID id) {
        MentorEntity mentor = mentorRepository.findById(id).orElseThrow(() -> new RegistrationServiceException("User not found", ErrorCode.NOT_FOUND_ERROR));
        mentorRepository.delete(mentor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<MentorDto> findByInternShip(String internship) {
        List<MentorEntity> entityList = mentorRepository.findByInternship(internship);
        if (entityList != null) {
            return mapper.listToListDto(entityList);
        } else {
            throw new RegistrationServiceException("No matches found", ErrorCode.NOT_FOUND_ERROR);
        }

    }

}
