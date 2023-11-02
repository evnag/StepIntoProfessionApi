package com.stepintoprofession.registration_service.model.entity;

import com.stepintoprofession.registration_service.validate.Phone;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Validated
public class BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @NotNull
    private String fullName;
    @Phone
    private String phoneNumber;
    @Email(message = "Email has invalid format: ${validatedValue}")
    private String email;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;
    @Past
    @NotNull
    private LocalDate birthday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public BaseEntity(String fullName, String phoneNumber, String email, Gender gender, LocalDate birthday, Address address) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
    }
}
