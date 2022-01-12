package learn.notbooking.data;

import learn.notbooking.models.Tier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class TierJdbcTemplateRepositoryTest {

    @Autowired
    TierJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void findAll() {
        List<Tier> all = repository.findAll();
        assertNotNull(all);

        assertTrue(all.size() >= 1);
    }

    @Test
    void findById() {

        Tier tierOne = new Tier(1, "tierName1", "Tier Decription One");
        Tier tierTwo = new Tier(2, "tierName2", "Tier Decription Two");

        Tier actual = repository.findById(1);
        assertEquals(tierOne, actual);

        actual = repository.findById(2);
        assertEquals(tierTwo, actual);

        actual = repository.findById(5000);
        assertEquals(null, actual);
    }

    @Test
    void shouldAdd() {
        Tier tier = new Tier();
        tier.setTierName("tierOne");
        tier.setTierDescription("Tier Description");


        Tier actual = repository.add(tier);
        tier.setTierId(32);
        assertNotNull(actual);
        assertEquals(tier, actual);
    }

    @Test
    void shouldUpdate() {
        Tier tier = new Tier();
        tier.setTierId(2);
        tier.setTierName("updatedTier");
        tier.setTierDescription("Updated Tier Description");


        assertTrue(repository.update(tier));
        assertEquals(tier, repository.findById(2));
    }

    @Test
    void shouldNotUpdateMissing() {
        Tier tier = new Tier();
        tier.setTierId(89347985);
        tier.setTierName("NotUpdatedTier");
        tier.setTierDescription("Not Updated Tier Description");


        assertFalse(repository.update(tier));

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