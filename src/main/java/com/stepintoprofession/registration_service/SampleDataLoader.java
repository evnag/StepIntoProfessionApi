package com.stepintoprofession.registration_service;

import com.github.javafaker.Faker;
import com.stepintoprofession.registration_service.repository.InternRepository;
import com.stepintoprofession.registration_service.repository.ProjectSeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

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

//        Gender[] genders = Gender.values();
//        List<String> languageSkills = new ArrayList<>();
//        languageSkills.add(faker.nation().language());
//
//        List<ProjectSeason> seasons = IntStream.range(0, 15)
//                .mapToObj(s -> new ProjectSeason(
//                        ++counter,
//                        faker.date().birthday()
//                )).collect(Collectors.toList());
//
//        List<InternEntity> interns = IntStream.range(0, 10)
//                .mapToObj(i -> new InternEntity(
//                        faker.name().fullName(),
//                        faker.phoneNumber().phoneNumber(),
//                        faker.internet().emailAddress(),
//                        genders[RANDOM.nextInt(genders.length)],
//                        faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
//                        new Address(
//                                faker.country().name(),
//                                faker.address().zipCode(),
//                                faker.address().city(),
//                                faker.address().stateAbbr(),
//                                faker.address().streetName(),
//                                faker.address().buildingNumber(),
//                                faker.number().digit()
//                        ),
//                        faker.job().position(),
//                        faker.number().numberBetween(1, 3),
//                        faker.artist().name(),
//                        languageSkills,
//                        faker.file().fileName(),
//                        faker.internet().url(),
//                        faker.internet().url(),
//                        seasons.stream()
//                                .skip((int) (seasons.size() * Math.random())).collect(Collectors.toList())
//                )).collect(Collectors.toList());
//
//
//        seasonRepository.saveAll(seasons);
//        internRepository.saveAll(interns);
    }
}
