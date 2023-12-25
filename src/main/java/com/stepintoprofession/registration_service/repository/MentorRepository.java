package com.stepintoprofession.registration_service.repository;

import com.stepintoprofession.registration_service.model.entity.Participants.MentorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MentorRepository extends JpaRepository<MentorEntity, UUID> {

    List<MentorEntity> findByInternship(String internship);

    Optional<MentorEntity> findByPhoneNumber(String phoneNumber);

    @Query(value = "select m from MentorEntity m where m.fullName like %:fullName%")
    Optional<MentorEntity> findMentorByFullNameLike(@Param("fullName") String fullName);
}
