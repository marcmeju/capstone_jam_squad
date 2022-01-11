package learn.notbooking.data;

import learn.notbooking.models.Package;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PackageJdbcTemplateRepositoryTest {

    @Autowired
    PackageJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void findAll() {
        List<Package> all = repository.findAll();
        assertNotNull(all);

        assertTrue(all.size() >= 1);
    }

    @Test
    void findById() {

        Package actual = repository.findById(1);
        assertNotNull(actual);
//        assertEquals(packOne, actual);

        actual = repository.findById(5000);
        assertEquals(null, actual);
    }

    @Test
    void shouldAdd() {
        Package pack = new Package();

        pack.setPackageName("packageName");
        pack.setTierId(1);
        pack.setPackagePrice(750);

        Package actual = repository.add(pack);
        pack.setPackageId(32);
        assertNotNull(actual);
        assertEquals(pack, actual);
    }

    @Test
    void shouldUpdate() {
        Package pack = new Package();
        pack.setPackageId(2);
        pack.setPackageName("updatedPackageName");
        pack.setTierId(1);
        pack.setPackagePrice(12345);

        assertTrue(repository.update(pack));
        assertEquals(pack, repository.findById(2));
    }

    @Test
    void shouldNotUpdateMissing() {
        Package pack = new Package();
        pack.setPackageId(89347985);
        pack.setPackageName("NotupdatedPackageName");
        pack.setTierId(1);
        pack.setPackagePrice(12343333);

        assertFalse(repository.update(pack));

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