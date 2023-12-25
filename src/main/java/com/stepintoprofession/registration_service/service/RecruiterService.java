package com.stepintoprofession.registration_service.service;

import com.stepintoprofession.registration_service.exception.ErrorCode;
import com.stepintoprofession.registration_service.exception.RegistrationServiceException;
import com.stepintoprofession.registration_service.mapper.RecruiterMapper;
import com.stepintoprofession.registration_service.model.dto.RecruiterDto;
import com.stepintoprofession.registration_service.model.entity.Participants.RecruiterEntity;
import com.stepintoprofession.registration_service.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final RecruiterMapper mapper;

    public RecruiterDto save(RecruiterDto dto) {
        return mapper.entityToDto(recruiterRepository.save(mapper.dtoToEntity(dto)));
    }

    public List<RecruiterDto> findALL() {
        return mapper.listToListDto(recruiterRepository.findAll());
    }

    public ResponseEntity<Void> delete(UUID id) {
        RecruiterEntity recruiter = recruiterRepository.findById(id)
                .orElseThrow(() -> new RegistrationServiceException(ErrorCode.RECRUITER_NOT_FOUND_ERROR
                        .getErrorMessage(id.toString()), ErrorCode.RECRUITER_NOT_FOUND_ERROR));
        recruiterRepository.delete(recruiter);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<RecruiterDto> findByInternShip(String internship) {
        List<RecruiterEntity> entityList = recruiterRepository.findByInternship(internship);
        if (entityList != null) {
            return mapper.listToListDto(entityList);
        } else {
            throw new RegistrationServiceException(ErrorCode.NO_MATCHES_FOUND_ERROR
                    .getErrorMessage(""), ErrorCode.NO_MATCHES_FOUND_ERROR);
        }
    }

    public RecruiterDto updateRecruiter(RecruiterDto dto) {
        RecruiterEntity entity = recruiterRepository.findByPhoneNumber(dto.getPhoneNumber())
                .orElseThrow(() -> new RegistrationServiceException(ErrorCode.RECRUITER_NOT_FOUND_ERROR
                        .getErrorMessage(dto.getPhoneNumber()), ErrorCode.RECRUITER_NOT_FOUND_ERROR));
        mapper.updateEntityFromDto(dto, entity);
        recruiterRepository.save(entity);
        return mapper.entityToDto(entity);
    }
}
