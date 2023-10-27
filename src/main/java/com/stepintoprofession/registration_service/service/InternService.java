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

    public InternDto save(InternEntity intern) {
        return mapper.entityToDto(internRepository.save(intern));
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
        return mapper.listToListDto(internRepository.findByInternship(internship));
    }

//    private List<String> projectListToListOfSeasonNumbers(List<ProjectSeason> projectSeasons) {
//        return projectSeasons.stream()
//                .map(p -> p.getSeasonNumber().toString())
//                .collect(Collectors.toList());
//    }
//
//    private List<List<ProjectSeason>> getListOfSeasons(List<InternEntity> entityList) {
//        return entityList.stream()
//                .map(InternEntity::getProjectId).collect(Collectors.toList());
//    }
//
//    private void setSeasonsToDtoList(List<InternDto> dtoList) {
//        dtoList.forEach(dto -> dto.setSeasonNumber(
//                internRepository.findAll().stream()
//                        .map(intern -> projectListToListOfSeasonNumbers(intern.getProjectId()))
//                        .findAny().orElse(null)));
//    }
//
//    private List<ProjectSeason> seasonNumbersToProjectSeasons(List<String> seasonNumbers) {
//        return seasonNumbers.stream()
//                .mapToInt(Integer::parseInt)
//                .mapToObj(seasonRepository::findProjectBySeasonNumber)
//                .collect(Collectors.toList());
//    }
}
