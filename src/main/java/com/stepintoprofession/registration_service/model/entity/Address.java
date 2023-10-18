package com.stepintoprofession.registration_service.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String country;
    private String zip;
    private String city;
    private String region;
    private String street;
    private String building;
    private String apartment;

    public Address(String country, String zip, String city, String region, String street, String building, String apartment) {
        this.country = country;
        this.zip = zip;
        this.city = city;
        this.region = region;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
    }
}
