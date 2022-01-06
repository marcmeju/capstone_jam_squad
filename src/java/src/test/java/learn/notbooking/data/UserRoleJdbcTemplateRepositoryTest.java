package learn.notbooking.data;

import learn.notbooking.models.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserRoleJdbcTemplateRepositoryTest {

    @Autowired
    UserRoleJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void findAll() {
        List<UserRole> all = repository.findAll();

        assertNotNull(all);
        // Anything 2 or bigger is good since pets are being added and deleted.
        assertTrue(all.size() >= 1);
    }

    @Test
    void findById() {
        UserRole admin = new UserRole(1, "NotAdmin");
        UserRole user = new UserRole(2, "NotUser");

        UserRole actual = repository.findById(1);
        assertEquals(admin, actual);

        actual = repository.findById(2);
        assertEquals(user, actual);

        actual = repository.findById(3);
        assertEquals(null, actual);
    }

    @Test
    void shouldAdd() {
        UserRole userRole = new UserRole();
        userRole.setRoleName("notAdmin");

        UserRole actual = repository.add(userRole);
        userRole.setUserRoleId(32);
        assertNotNull(actual);
        assertEquals(userRole, actual);
    }

    @Test
    void shouldUpdate() {
        UserRole userRole = new UserRole();
        userRole.setUserRoleId(2);
        userRole.setRoleName("Anonymous");

        assertTrue(repository.update(userRole));
        assertEquals(userRole, repository.findById(2));
    }

    @Test
    void shouldNotUpdateMissing() {
        UserRole userRole = new UserRole();
        userRole.setUserRoleId(670000);
        userRole.setRoleName("notAnonymous");

        assertFalse(repository.update(userRole));

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
