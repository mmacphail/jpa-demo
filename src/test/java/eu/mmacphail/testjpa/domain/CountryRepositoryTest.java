package eu.mmacphail.testjpa.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CountryRepositoryTest {

    @Autowired
    CountryRepository countryRepository;

    @Test
    @Transactional
    public void canSaveCountry() {
        Country country = new Country();
        country.setName("Scotland");
        countryRepository.save(country);
        assertNotNull(country.getId());

        Monument lochNess = new Monument();
        lochNess.setName("Loch Ness");
        country.getMonuments().add(lochNess);

        Monument edinburghCastle = new Monument();
        edinburghCastle.setName("Edinburgh Castle");
        country.getMonuments().add(edinburghCastle);

        countryRepository.save(country);

        Country savedCountry = countryRepository.findById(country.getId()).get();
        assertEquals(2, savedCountry.getMonuments().size());
        savedCountry.getMonuments().forEach(monument -> System.out.println(monument.getName()));
    }

}