package com.stepintoprofession.registration_service;

import com.github.javafaker.Faker;
import com.stepintoprofession.registration_service.model.entity.Address;
import com.stepintoprofession.registration_service.model.entity.InternEntity;
import com.stepintoprofession.registration_service.repository.InternRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private final InternRepository internRepository;
    private final Faker faker;

    @Override
    public void run(String... args) throws Exception {

        List<InternEntity> interns = IntStream.range(0, 10)
                .mapToObj(i -> new InternEntity(
                        faker.name().fullName(),
                        faker.phoneNumber().phoneNumber(),
                        faker.internet().emailAddress(),
                        faker.demographic().sex(),
                        faker.date().birthday(),
                        new Address(
                                faker.address().streetAddress(),
                                faker.address().city(),
                                faker.address().state(),
                                faker.address().zipCode()
                        ),
                        faker.job().position()
                )).collect(Collectors.toList());

        internRepository.saveAll(interns);
    }
}
