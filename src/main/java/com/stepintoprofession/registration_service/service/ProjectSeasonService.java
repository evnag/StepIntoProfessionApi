package com.stepintoprofession.registration_service.service;

import com.stepintoprofession.registration_service.exception.ErrorCode;
import com.stepintoprofession.registration_service.exception.RegistrationServiceException;
import com.stepintoprofession.registration_service.mapper.ProjectSeasonMapper;
import com.stepintoprofession.registration_service.model.dto.ProjectSeasonDto;
import com.stepintoprofession.registration_service.model.entity.ProjectSeason;
import com.stepintoprofession.registration_service.repository.ProjectSeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectSeasonService {

    private final ProjectSeasonRepository seasonRepository;
    private final ProjectSeasonMapper mapper;

    public ProjectSeasonDto save(ProjectSeasonDto dto) {
        return mapper.entityToDto(seasonRepository.save(mapper.dtoToEntity(dto)));
    }

    public List<ProjectSeasonDto> findAll() {
        return mapper.listToListDto(seasonRepository.findAll());
    }

    public ProjectSeasonDto getProjectSeason(Integer seasonNumber) {
        ProjectSeason season = seasonRepository.findBySeasonNumber(seasonNumber)
                .orElseThrow(() -> new RegistrationServiceException(ErrorCode.SEASON_NOT_FOUND_ERROR
                        .getErrorMessage(seasonNumber.toString()), ErrorCode.SEASON_NOT_FOUND_ERROR));
        return mapper.entityToDto(season);
    }

    public ResponseEntity<Void> delete(Integer seasonNumber) {
        ProjectSeason season = seasonRepository.findBySeasonNumber(seasonNumber)
                .orElseThrow(() -> new RegistrationServiceException(ErrorCode.SEASON_NOT_FOUND_ERROR
                        .getErrorMessage(seasonNumber.toString()), ErrorCode.SEASON_NOT_FOUND_ERROR));
        seasonRepository.delete(season);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ProjectSeasonDto updateSeason(ProjectSeasonDto dto) {
        ProjectSeason entity = seasonRepository.findBySeasonNumber(dto.getSeasonNumber())
                .orElseThrow(() -> new RegistrationServiceException(ErrorCode.SEASON_NOT_FOUND_ERROR
                        .getErrorMessage(dto.getSeasonNumber().toString()), ErrorCode.SEASON_NOT_FOUND_ERROR));
        mapper.updateEntityFromDto(dto, entity);
        seasonRepository.save(entity);
        return mapper.entityToDto(entity);
    }
}
