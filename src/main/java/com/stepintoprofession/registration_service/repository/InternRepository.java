package com.stepintoprofession.registration_service.repository;

import com.stepintoprofession.registration_service.model.entity.Participants.InternEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InternRepository extends JpaRepository<InternEntity, UUID> {

    List<InternEntity> findByInternship(String internship);

    Optional<InternEntity> findByPhoneNumber(String phoneNumber);

    @Query(value = "select i from InternEntity i where i.fullName like %:fullName%")
    Optional<InternEntity> findInternByFullNameLike(@Param("fullName")String fullName);
}
