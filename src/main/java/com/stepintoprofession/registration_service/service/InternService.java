package com.stepintoprofession.registration_service.service;

import com.stepintoprofession.registration_service.exception.InternNotFoundException;
import com.stepintoprofession.registration_service.model.Intern;
import com.stepintoprofession.registration_service.repository.InternRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternService {

    private final InternRepository internRepository;

    public Intern save(Intern intern) {
        return internRepository.save(intern);
    }

    public List<Intern> findALL() {
        return internRepository.findAll();
    }

    public Intern findByFirstNamesAndLastName(String firstName, String lastName) {
        return internRepository.findByFirstNameEqualsAndLastNameEquals(firstName, lastName)
                .orElseThrow(() -> new InternNotFoundException(firstName, lastName));
    }

    public ResponseEntity<Void> delete(Integer id) {
        Intern intern = internRepository.findById(id).orElseThrow(() -> new InternNotFoundException(id));
        internRepository.delete(intern);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
