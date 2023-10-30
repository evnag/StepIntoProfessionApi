package com.stepintoprofession.registration_service.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "recruiter")
@Validated
public class RecruiterEntity extends BaseEntity {

    @NotNull
    private String internship;
    @NotNull
    private String company;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "intern_id", referencedColumnName = "id")
    private InternEntity intern;

    @ManyToMany
    @JoinTable(
            name = "project_recruiter",
            joinColumns = @JoinColumn(name = "recruiter_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    @NotEmpty(message = "Season list cannot be empty.")
    private List<ProjectSeason> projects;

    public RecruiterEntity(String fullName, String phoneNumber, String email, Gender gender, LocalDate birthday, Address address, String internship, String company, InternEntity intern, List<ProjectSeason> projects) {
        super(fullName, phoneNumber, email, gender, birthday, address);
        this.internship = internship;
        this.company = company;
        this.intern = intern;
        this.projects = projects;
    }
}
