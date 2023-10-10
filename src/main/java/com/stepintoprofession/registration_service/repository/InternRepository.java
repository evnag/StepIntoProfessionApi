package com.stepintoprofession.registration_service.repository;

import com.stepintoprofession.registration_service.model.entity.InternEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternRepository extends JpaRepository<InternEntity, Integer> {

}
