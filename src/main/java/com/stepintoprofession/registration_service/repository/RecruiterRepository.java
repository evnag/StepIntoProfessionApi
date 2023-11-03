package com.stepintoprofession.registration_service.repository;

import com.stepintoprofession.registration_service.model.entity.Participants.RecruiterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecruiterRepository extends JpaRepository<RecruiterEntity, UUID> {

    List<RecruiterEntity> findByInternship(String internship);

    Optional<RecruiterEntity> findByPhoneNumber(String phoneNumber);
}
