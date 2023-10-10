package com.stepintoprofession.registration_service.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "recruiter")
public class RecruiterEntity extends BaseEntity {

    private String internship;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "intern_id", referencedColumnName = "id")
    private InternEntity intern;

}
