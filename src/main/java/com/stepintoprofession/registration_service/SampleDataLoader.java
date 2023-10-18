package com.stepintoprofession.registration_service;

import com.github.javafaker.Faker;
import com.stepintoprofession.registration_service.model.entity.Address;
import com.stepintoprofession.registration_service.model.entity.Gender;
import com.stepintoprofession.registration_service.model.entity.InternEntity;
import com.stepintoprofession.registration_service.model.entity.ProjectSeason;
import com.stepintoprofession.registration_service.repository.InternRepository;
import com.stepintoprofession.registration_service.repository.ProjectSeasonRepository;
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
    private final ProjectSeasonRepository seasonRepository;
    private final Faker faker;
    private static final Random RANDOM = new Random();
    private int counter = 0;

    @Override
    public void run(String... args) throws Exception {

        Gender[] genders = Gender.values();

        List<ProjectSeason> seasons = IntStream.range(0, 15)
                .mapToObj(s -> new ProjectSeason(
                        ++counter,
                        faker.date().birthday()
                )).collect(Collectors.toList());

        List<InternEntity> interns = IntStream.range(0, 10)
                .mapToObj(i -> new InternEntity(
                        faker.name().fullName(),
                        faker.phoneNumber().phoneNumber(),
                        faker.internet().emailAddress(),
                        genders[RANDOM.nextInt(genders.length)],
                        faker.date().birthday(),
                        new Address(
                                faker.country().name(),
                                faker.address().zipCode(),
                                faker.address().city(),
                                faker.address().stateAbbr(),
                                faker.address().streetName(),
                                faker.address().buildingNumber(),
                                faker.number().digit()
                        ),
                        faker.job().position()
                )).collect(Collectors.toList());


        seasonRepository.saveAll(seasons);
        internRepository.saveAll(interns);
    }
}
