package com.bsoft.mptest;

import com.bsoft.mptest.database.AdresDAO;
import com.bsoft.mptest.repositories.AdresRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("${activeProfile}")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class MptestApplicationTests {

    private final String activeProfile = System.getenv("activeProfile");
    @Autowired
    private AdresRepository adresRepository;
    // Your test methods here

    @Test
    @Order(1)
    public void contextLoads() {
        log.info("contextLoads profile: {}", activeProfile);
    }

    @Test
    @Transactional
    @Order(2)
    public void clearAdresses() {
        log.info("clearAdresses profile: {}", activeProfile);

        adresRepository.findAll().forEach(adres -> {
            log.info("clearAdresses delete adres: {}", adres);
            adresRepository.deleteById(adres.getId());
        });
    }

    @Test
    @Transactional
    @Order(3)
    public void loadAdresses() {
        log.info("loadAdresses profile: {}", activeProfile);

        try {
            int maxAdresses = 20;
            ArrayList<AdresDAO> adresses = new ArrayList<>();
            for (int i = 0; i < maxAdresses; i++) {
                adresses.add(new AdresDAO("Kerkewijk", Integer.toString(i), "3904 NL", "Veenendaal"));
                log.info("loadAdresses load adres: {}", adresses.get(i));
            }

            adresses.forEach(adres -> {
                adresRepository.save(adres);
            });


        } catch (Exception e) {
            log.error("loadAdresses unexpected exception", e);
        }
    }

    @Test
    @Order(4)
    public void adresTest() {
        log.info("adresTest profile: {}", activeProfile);

        adresRepository.findAll()
                .forEach(adres -> {
                    log.info("adresTest - adres: {}", adres);
                });
    }
}
