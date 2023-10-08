package com.stepintoprofession.registration_service.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Intern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String gender;
    private Date birthday;
    private String internship;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public Intern(String firstName, String middleName, String lastName, String phoneNumber, String email, String gender, Date birthday, String internship, Address address) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.internship = internship;
        this.address = address;
    }
}
