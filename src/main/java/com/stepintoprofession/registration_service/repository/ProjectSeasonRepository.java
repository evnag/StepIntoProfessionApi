package com.stepintoprofession.registration_service.repository;

import com.stepintoprofession.registration_service.model.entity.ProjectSeason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectSeasonRepository extends JpaRepository<ProjectSeason, UUID> {

    ProjectSeason findProjectBySeasonNumber(Integer seasonNumber);

    Optional<ProjectSeason> findBySeasonNumber(Integer seasonNumber);
}
