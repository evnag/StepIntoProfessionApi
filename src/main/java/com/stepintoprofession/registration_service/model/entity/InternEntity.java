package com.stepintoprofession.registration_service.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "intern")
public class InternEntity extends BaseEntity {

    private String internship;
    private Integer disabilityGroup;
    private String disabilityType;
    private String languageSkill;

    private String cvPath;
    private String videoCvPath;
    private String tildaCvPath;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectSeason projectId;

    public InternEntity(String fullName, String phoneNumber, String email, Gender gender, LocalDate birthday, Address address, String internship, Integer disabilityGroup, String disabilityType, String languageSkill, String cvPath, String videoCvPath, String tildaCvPath, ProjectSeason projectId) {
        super(fullName, phoneNumber, email, gender, birthday, address);
        this.internship = internship;
        this.disabilityGroup = disabilityGroup;
        this.disabilityType = disabilityType;
        this.languageSkill = languageSkill;
        this.cvPath = cvPath;
        this.videoCvPath = videoCvPath;
        this.tildaCvPath = tildaCvPath;
        this.projectId = projectId;
    }
}
