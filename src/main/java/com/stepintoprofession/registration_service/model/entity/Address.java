package com.stepintoprofession.registration_service.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Validated
public class Address {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @NotNull
    private String country;
    @NotNull
    private String zip;
    @NotNull
    private String city;
    @NotNull
    private String region;
    @NotNull
    private String street;
    @NotNull
    private String building;
    @NotNull
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
