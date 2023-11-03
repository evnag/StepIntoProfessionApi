package com.stepintoprofession.registration_service.model.entity;

import com.stepintoprofession.registration_service.model.entity.Participants.InternEntity;
import com.stepintoprofession.registration_service.model.entity.Participants.MentorEntity;
import com.stepintoprofession.registration_service.model.entity.Participants.RecruiterEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
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
    private LocalDate startDate;

    @ManyToMany(mappedBy = "projectId")
//    @NotEmpty(message = "Interns list cannot be empty.")
    private List<InternEntity> interns;
    @ManyToMany(mappedBy = "projects")
//    @NotEmpty(message = "Mentors list cannot be empty.")
    private List<MentorEntity> mentors;
    @ManyToMany(mappedBy = "projects")
//    @NotEmpty(message = "Recruiters list cannot be empty.")
    private List<RecruiterEntity> recruiters;

    public ProjectSeason(Integer seasonNumber, LocalDate startDate) {
        this.seasonNumber = seasonNumber;
        this.startDate = startDate;
    }
}
