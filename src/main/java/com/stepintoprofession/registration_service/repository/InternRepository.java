package com.stepintoprofession.registration_service.repository;

import com.stepintoprofession.registration_service.model.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternRepository extends JpaRepository<Intern, Integer> {

    Intern findByFirstNameEqualsAndLastNameEquals(String firstName, String lastName);

    void deleteById(Integer id);
}
