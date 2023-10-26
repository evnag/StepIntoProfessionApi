package com.stepintoprofession.registration_service.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class ProjectSeason {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private Integer seasonNumber;
    private Date startDate;

    @ManyToMany(mappedBy = "projectId")
    private List<InternEntity> interns;
    @ManyToMany(mappedBy = "projects")
    private List<MentorEntity> mentors;
    @ManyToMany(mappedBy = "projects")
    private List<RecruiterEntity> recruiters;

    public ProjectSeason(Integer seasonNumber, Date startDate) {
        this.seasonNumber = seasonNumber;
        this.startDate = startDate;
    }
}
