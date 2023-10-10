package com.stepintoprofession.registration_service.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "intern")
public class InternEntity extends BaseEntity {

    private String internship;

    public InternEntity(String fullName, String phoneNumber, String email, String gender, Date birthday, Address address, String internship) {
        super(fullName, phoneNumber, email, gender, birthday, address);
        this.internship = internship;
    }
}
