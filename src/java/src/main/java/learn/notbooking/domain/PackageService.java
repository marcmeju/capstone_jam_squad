package learn.notbooking.domain;

import learn.notbooking.data.PackageRepository;
import learn.notbooking.models.Package;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {
    private final PackageRepository repository;

    public PackageService(PackageRepository repository) {
        this.repository = repository;
    }


    public Package findById(int packId){return repository.findById(packId);}


    public List<Package> findAll() {
        return repository.findAll();
    }


    public Result<Package> add(Package pack) {
        Result<Package> result = validate(pack);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        Package p = repository.add(pack);
        result.setPayload(p);
        return result;
    }


    public Result<Package> update(Package pack) {
        Result<Package> result = validate(pack);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        if (!repository.update(pack)) {
            result.addMessage("pack id " + pack.getPackageId() + " not found", ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int packId) {
        return repository.deleteById(packId);
    }





    // ********************** validate ************************
    private Result<Package> validate(Package pack) {
        Result<Package> result = new Result<>();

        if (pack == null) {
            result.addMessage("package cannot be null", ResultType.INVALID);
            return result;
        }

        if (pack.getPackageName() == null || pack.getPackageName().trim().length() == 0) {
            result.addMessage("Package name is required", ResultType.INVALID);
        }

        if (pack.getTierId() <= 0 ) {
            result.addMessage("Tier ID is required", ResultType.INVALID);
        }

        if (pack.getPackagePrice() <= 0 ) {
            result.addMessage("A valid Package price is required", ResultType.INVALID);
        }

        for(Package sc : findAll()){
            if (pack.getPackageName().trim().equalsIgnoreCase(sc.getPackageName())){
                result.addMessage("Package name already exists", ResultType.INVALID);
            }
        }


        return result;
    }
}
