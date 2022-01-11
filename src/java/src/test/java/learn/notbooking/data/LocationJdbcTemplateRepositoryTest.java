package learn.notbooking.data;

import learn.notbooking.models.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LocationJdbcTemplateRepositoryTest {

    @Autowired
    LocationJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void findAll() {
        List<Location> all = repository.findAll();
        assertNotNull(all);

        assertTrue(all.size() >= 1);
    }

    @Test
    void findById() {

        Location locationOne = new Location(1, "location1Address", "location1City","location1State",12345);
        Location locationTwo = new Location(2, "location2Address", "location2City","location2State",21534);

        Location actual = repository.findById(1);
        assertEquals(locationOne, actual);

        actual = repository.findById(2);
        assertEquals(locationTwo, actual);

        actual = repository.findById(5000);
        assertEquals(null, actual);
    }

    @Test
    void shouldAdd() {
        Location location = new Location();

        location.setLocationAddress("locationAddress");
        location.setLocationCity("LocationCity");
        location.setLocationState("LocationState");
        location.setLocationZipCode(1);

        Location actual = repository.add(location);
        location.setLocationId(32);
        assertNotNull(actual);
        assertEquals(location, actual);
    }

    @Test
    void shouldUpdate() {
        Location location = new Location();
        location.setLocationId(2);
        location.setLocationAddress("updatedLocationAddress");
        location.setLocationCity("updatedLocationCity");
        location.setLocationState("Location State");
        location.setLocationZipCode(12345);


        assertTrue(repository.update(location));
        assertEquals(location, repository.findById(2));
    }

    @Test
    void shouldNotUpdateMissing() {
        Location location = new Location();
        location.setLocationId(89347985);
        location.setLocationAddress("NotUpdatedLocationAddress");
        location.setLocationCity("NotUpdatedLocationCity");
        location.setLocationState("NotUpdatedLocationState");
        location.setLocationZipCode(12345);

        assertFalse(repository.update(location));

    }


    @Test
    void shouldDeleteExisting() {
        assertTrue(repository.deleteById(2));
    }

    @Test
    void shouldNotDeleteMissing() {
        assertFalse(repository.deleteById(40000));
    }

}