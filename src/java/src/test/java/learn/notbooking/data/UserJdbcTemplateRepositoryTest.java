//package learn.notbooking.data;
//
//import learn.notbooking.models.AppUser;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//class UserJdbcTemplateRepositoryTest {
//
//    @Autowired
//    UserJdbcTemplateRepository repository;
//
//    @Autowired
//    KnownGoodState knownGoodState;
//
//    @BeforeEach
//    void setup() {
//        knownGoodState.set();
//    }
//
//
//    @Test
//    void findAll() {
//        List<AppUser> all = repository.findAll();
//        assertNotNull(all);
//
//        assertTrue(all.size() >= 1);
//    }
//
//    @Test
//    void findById() {
//
//        AppUser userOne = new AppUser(1, "userName1", "fdggerqgegeg", 1);
//        AppUser userTwo = new AppUser(2, "userName2", "fdsfgrehrthfdg", 2);
//
//        AppUser actual = repository.findById(1);
//        assertEquals(userOne, actual);
//
//        actual = repository.findById(2);
//        assertEquals(userTwo, actual);
//
//        actual = repository.findById(5000);
//        assertEquals(null, actual);
//    }
//
//    @Test
//    void shouldAdd() {
//        AppUser user = new AppUser();
//        user.setUserName("userOne");
//        user.setPasswordHash("jngdgkjb");
//        user.setUserRoleId(1);
//
//        AppUser actual = repository.add(user);
//        user.setUserId(32);
//        assertNotNull(actual);
//        assertEquals(user, actual);
//    }
//
//    @Test
//    void shouldUpdate() {
//        AppUser user = new AppUser();
//        user.setUserId(2);
//        user.setUserName("updatedUser");
//        user.setPasswordHash("jngdgkjb");
//        user.setUserRoleId(1);
//
//        assertTrue(repository.update(user));
//        assertEquals(user, repository.findById(2));
//    }
//
//    @Test
//    void shouldNotUpdateMissing() {
//        AppUser user = new AppUser();
//        user.setUserId(89347985);
//        user.setUserName("NotUpdatedUser");
//        user.setPasswordHash("jngdgkjb");
//        user.setUserRoleId(1);
//
//        assertFalse(repository.update(user));
//
//    }
//
//
//    @Test
//    void shouldDeleteExisting() {
//        assertTrue(repository.deleteById(2));
//    }
//
//    @Test
//    void shouldNotDeleteMissing() {
//        assertFalse(repository.deleteById(40000));
//    }
//
//}