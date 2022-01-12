package learn.notbooking.domain;

import learn.notbooking.data.LocationRepository;
import learn.notbooking.models.Location;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }


    public Location findById(int locationId){return repository.findById(locationId);}


    public List<Location> findAll() {
        return repository.findAll();
    }


    public Result<Location> add(Location location) {
        Result<Location> result = validate(location);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        Location p = repository.add(location);
        result.setPayload(p);
        return result;
    }


    public Result<Location> update(Location location) {
        Result<Location> result = validate(location);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        if (!repository.update(location)) {
            result.addMessage("location id " + location.getLocationId() + " not found", ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int locationId) {
        return repository.deleteById(locationId);
    }





    // ********************** validate ************************
    private Result<Location> validate(Location location) {
        Result<Location> result = new Result<>();

        if (location == null) {
            result.addMessage("location cannot be null", ResultType.INVALID);
            return result;
        }

        if (location.getLocationCity().trim().length() <=0) {
            result.addMessage("Location city is required", ResultType.INVALID);
        }

        if (location.getLocationState().trim().length() <=0) {
            result.addMessage("Location State is required", ResultType.INVALID);
        }

        if (location.getLocationAddress().trim().length() <=0) {
            result.addMessage("Location Address is required", ResultType.INVALID);
        }
        if (location.getLocationZipCode() <=0 ||String.valueOf(location.getLocationZipCode()).trim().length() != 5   ) {
            result.addMessage("Location Zipcode is invalid", ResultType.INVALID);
        }

        for(Location sc : findAll()){
            if (location == sc){
                result.addMessage("This location already exists", ResultType.INVALID);
            }

        }


        return result;
    }

}
