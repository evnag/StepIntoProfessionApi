package com.stepintoprofession.registration_service.repository;

import com.stepintoprofession.registration_service.model.entity.Participants.MentorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MentorRepository extends JpaRepository<MentorEntity, UUID> {

    List<MentorEntity> findByInternship(String internship);
}
