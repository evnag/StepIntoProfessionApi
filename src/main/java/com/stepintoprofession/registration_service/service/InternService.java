package com.stepintoprofession.registration_service.service;

import com.stepintoprofession.registration_service.exception.ErrorCode;
import com.stepintoprofession.registration_service.exception.RegistrationServiceException;
import com.stepintoprofession.registration_service.mapper.InternMapper;
import com.stepintoprofession.registration_service.model.dto.InternDto;
import com.stepintoprofession.registration_service.model.entity.InternEntity;
import com.stepintoprofession.registration_service.repository.InternRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InternService {

    private final InternRepository internRepository;
    private final InternMapper mapper;

    public InternDto save(InternDto dto) {
        if (dto == null) {
            return null;
        }
        return mapper.entityToDto(internRepository.save(mapper.dtoToEntity(dto)));
    }

    public List<InternDto> findALL() {
        return mapper.listToListDto(internRepository.findAll());
    }

    public ResponseEntity<Void> delete(UUID id) {
        InternEntity intern = internRepository.findById(id).orElseThrow(() -> new RegistrationServiceException("User not found", ErrorCode.NOT_FOUND_ERROR));
        internRepository.delete(intern);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<InternDto> findByInternShip(String internship) {
        List<InternEntity> entityList = internRepository.findByInternship(internship);
        if (entityList != null) {
            return mapper.listToListDto(entityList);
        } else {
            throw new RegistrationServiceException("No matches found", ErrorCode.NOT_FOUND_ERROR);
        }
    }

}
