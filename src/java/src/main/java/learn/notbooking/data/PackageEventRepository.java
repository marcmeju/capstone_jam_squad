package learn.notbooking.data;

import learn.notbooking.models.PackageEvent;

public interface PackageEventRepository {
    boolean add(PackageEvent packageEvent);

    boolean update(PackageEvent packageEvent);

    boolean deleteByKey(int packageEventId);
}
