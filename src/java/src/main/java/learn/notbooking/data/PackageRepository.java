package learn.notbooking.data;

import learn.notbooking.models.Package;

import java.util.List;

public interface PackageRepository {
    List<Package> findAll();
    Package findById(int packId);

    Package add(Package pack);

    boolean update(Package pack);

    boolean deleteById(int packId);
}
