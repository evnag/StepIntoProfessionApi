package com.stepintoprofession.registration_service.service;

import com.stepintoprofession.registration_service.exception.ErrorCode;
import com.stepintoprofession.registration_service.exception.RegistrationServiceException;
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

    public InternEntity save(InternEntity intern) {
        return internRepository.save(intern);
    }

    public List<InternEntity> findALL() {
        return internRepository.findAll();
    }

    public ResponseEntity<Void> delete(UUID id) {
        InternEntity intern = internRepository.findById(id).orElseThrow(() -> new RegistrationServiceException("User not found", ErrorCode.NOT_FOUND_ERROR));
        internRepository.delete(intern);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
