package learn.notbooking.domain;

import learn.notbooking.data.TierRepository;
import learn.notbooking.models.Tier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TierService {
    private final TierRepository repository;

    public TierService(TierRepository repository) {
        this.repository = repository;
    }


    public Tier findById(int tierId){return repository.findById(tierId);}


    public List<Tier> findAll() {
        return repository.findAll();
    }


    public Result<Tier> add(Tier tier) {
        Result<Tier> result = validate(tier);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        Tier p = repository.add(tier);
        result.setPayload(p);
        return result;
    }


    public Result<Tier> update(Tier tier) {
        Result<Tier> result = validate(tier);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        if (!repository.update(tier)) {
            result.addMessage("tier id " + tier.getTierId() + " not found", ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int tierId) {
        return repository.deleteById(tierId);
    }





    // ********************** validate ************************
    private Result<Tier> validate(Tier tier) {
        Result<Tier> result = new Result<>();

        if (tier == null) {
            result.addMessage("tier cannot be null", ResultType.INVALID);
            return result;
        }

        if (tier.getTierName() == null || tier.getTierName().trim().length() == 0) {
            result.addMessage("name is required", ResultType.INVALID);
        }

        if (tier.getTierDescription() == null || tier.getTierDescription().trim().length() == 0) {
            result.addMessage("Description is required", ResultType.INVALID);
        }

        for(Tier sc : findAll()){
            if (tier.getTierName().trim().equalsIgnoreCase(sc.getTierName())){
                result.addMessage("name already exists", ResultType.INVALID);
            }
        }


        return result;
    }
}
