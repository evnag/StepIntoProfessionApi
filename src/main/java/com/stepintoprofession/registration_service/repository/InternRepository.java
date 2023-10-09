package com.stepintoprofession.registration_service.repository;

import com.stepintoprofession.registration_service.model.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InternRepository extends JpaRepository<Intern, Integer> {

    Optional<Intern> findByFirstNameEqualsAndLastNameEquals(String firstName, String lastName);
}
