package com.stepintoprofession.registration_service.model.entity.Participants;

import com.stepintoprofession.registration_service.model.entity.Address;
import com.stepintoprofession.registration_service.model.entity.BaseEntity;
import com.stepintoprofession.registration_service.model.entity.Gender;
import com.stepintoprofession.registration_service.model.entity.ProjectSeason;
import com.stepintoprofession.registration_service.utils.converter.StringListConverter;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "intern")
@Validated
public class InternEntity extends BaseEntity {

    @NotNull
    private String internship;
    @NotNull
    private Integer disabilityGroup;
    @NotNull
    private String disabilityType;

    @Convert(converter = StringListConverter.class)
    @Column(name = "language_skill", nullable = false)
    @Builder.Default
    @NotEmpty(message = "Language skill cannot be empty.")
    private List<String> languageSkill = new ArrayList<>();
    private String cvPath;
    private String videoCvPath;
    private String tildaCvPath;


    @ManyToMany
    @JoinTable(
            name = "project_intern",
            joinColumns = @JoinColumn(name = "intern_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    @NotEmpty(message = "Season list cannot be empty.")
    private List<ProjectSeason> projectId;

    public InternEntity(String fullName, String phoneNumber, String email, Gender gender, LocalDate birthday, Address address, String internship, Integer disabilityGroup, String disabilityType, List<String> languageSkill, String cvPath, String videoCvPath, String tildaCvPath, List<ProjectSeason> projectId) {
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
