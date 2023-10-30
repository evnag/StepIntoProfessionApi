package com.stepintoprofession.registration_service.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
@Validated
public class ProjectSeason {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @NotNull
    private Integer seasonNumber;
    @PastOrPresent
    private Date startDate;

    @ManyToMany(mappedBy = "projectId")
    @NotEmpty(message = "Interns list cannot be empty.")
    private List<InternEntity> interns;
    @ManyToMany(mappedBy = "projects")
    @NotEmpty(message = "Mentors list cannot be empty.")
    private List<MentorEntity> mentors;
    @ManyToMany(mappedBy = "projects")
    @NotEmpty(message = "Recruiters list cannot be empty.")
    private List<RecruiterEntity> recruiters;

    public ProjectSeason(Integer seasonNumber, Date startDate) {
        this.seasonNumber = seasonNumber;
        this.startDate = startDate;
    }
}
