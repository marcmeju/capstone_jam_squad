package learn.notbooking.data;

import learn.notbooking.models.Tier;

import java.util.List;

public interface TierRepository {
    List<Tier> findAll();
    Tier findById(int tierId);

    Tier add(Tier tier);

    boolean update(Tier tier);

    boolean deleteById(int tierId);
}
