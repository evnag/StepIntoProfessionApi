package com.stepintoprofession.registration_service.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "mentor")
public class MentorEntity extends BaseEntity {

    private String internship;
    private String company;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "intern_id", referencedColumnName = "id")
    private InternEntity intern;

    @ManyToMany
    @JoinTable(
            name = "project_mentor",
            joinColumns = @JoinColumn(name = "mentor_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<ProjectSeason> projects;

    public MentorEntity(String fullName, String phoneNumber, String email, Gender gender, LocalDate birthday, Address address, String internship, String company, InternEntity intern, List<ProjectSeason> projects) {
        super(fullName, phoneNumber, email, gender, birthday, address);
        this.internship = internship;
        this.company = company;
        this.intern = intern;
        this.projects = projects;
    }
}
