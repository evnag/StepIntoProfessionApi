package com.stepintoprofession.registration_service;

import com.github.javafaker.Faker;
import com.stepintoprofession.registration_service.model.entity.Address;
import com.stepintoprofession.registration_service.model.entity.Gender;
import com.stepintoprofession.registration_service.model.entity.InternEntity;
import com.stepintoprofession.registration_service.repository.InternRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private final InternRepository internRepository;
    private final Faker faker;
    private static final Random RANDOM = new Random();

    @Override
    public void run(String... args) throws Exception {

        Gender[] genders = Gender.values();

        List<InternEntity> interns = IntStream.range(0, 10)
                .mapToObj(i -> new InternEntity(
                        faker.name().fullName(),
                        faker.phoneNumber().phoneNumber(),
                        faker.internet().emailAddress(),
                        genders[RANDOM.nextInt(genders.length)],
                        faker.date().birthday(),
                        new Address(
                                faker.address().zipCode(),
                                faker.address().city(),
                                faker.address().stateAbbr(),
                                faker.address().streetName(),
                                faker.address().buildingNumber(),
                                faker.number().digit()
                        ),
                        faker.job().position()
                )).collect(Collectors.toList());

        internRepository.saveAll(interns);
    }
}
