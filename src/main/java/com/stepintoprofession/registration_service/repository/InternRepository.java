package com.stepintoprofession.registration_service.repository;

import com.stepintoprofession.registration_service.model.entity.Participants.InternEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InternRepository extends JpaRepository<InternEntity, UUID> {

    List<InternEntity> findByInternship(String internship);
}
