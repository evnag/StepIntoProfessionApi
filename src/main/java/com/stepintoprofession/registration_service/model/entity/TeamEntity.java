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
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "team")
@Validated
public class TeamEntity {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @OneToOne
    @JoinColumn(name = "intern_id")
    private InternEntity intern;
    @OneToOne
    @JoinColumn(name = "mentor_id")
    private MentorEntity mentor;
    @OneToOne
    @JoinColumn(name = "recruiter_id")
    private RecruiterEntity recruiter;
    @NotBlank
    private String internship;
    @OneToOne
    @JoinColumn(name = "season_number", referencedColumnName = "seasonNumber", nullable = false)
    private ProjectSeason season;

}
