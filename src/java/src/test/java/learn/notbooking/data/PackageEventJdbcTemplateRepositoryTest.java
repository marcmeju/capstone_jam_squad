package learn.notbooking.data;

import learn.notbooking.models.Event;
import learn.notbooking.models.Package;
import learn.notbooking.models.PackageEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PackageEventJdbcTemplateRepositoryTest {

    @Autowired
    PackageEventJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void shouldAdd() {
        PackageEvent packageEvent = makePackageEvent();
      assertTrue( repository.add(packageEvent));

        try {
            repository.add(packageEvent); // must fail
            fail("cannot add an event to an package twice.");
        } catch (DataAccessException ex) {
            // this is expected.
        }
    }

    @Test
    void shouldUpdate() {
        PackageEvent packageEvent = makePackageEvent();
        packageEvent.setPackageEventId(32);
        packageEvent.getEvent().setEventId(2);
        assertTrue(repository.update(packageEvent));

        packageEvent.setPackageId(675);
        assertFalse(repository.update(packageEvent));

    }


    @Test
    void shouldDeleteExisting() {
        assertTrue(repository.deleteByKey(1));
    }

    @Test
    void shouldNotDeleteMissing() {
        assertFalse(repository.deleteByKey(40000));
    }


    PackageEvent makePackageEvent(){

        PackageEvent packageEvent = new PackageEvent();
        packageEvent.setPackageId(1);
        packageEvent.setEventId(1);
        packageEvent.setPackageEventId(32);


        Event event = new Event();
        event.setEventType("eventType");
        event.setEventName("EventName");
        event.setEventDate("EventDate");
        event.setEventPrice(150);
        event.setLocationId(1);
        event.setContactId(1);


        Package pack = new Package();
        pack.setPackageName("packageName");
        pack.setTierId(1);
        pack.setPackagePrice(750);

        return packageEvent;
    }
}