package learn.notbooking.domain;

import learn.notbooking.data.UserRoleRepository;
import learn.notbooking.models.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    private final UserRoleRepository repository;

    public UserRoleService(UserRoleRepository repository) {
        this.repository = repository;
    }


    public UserRole findById(int userRoleId){return repository.findById(userRoleId);}


    public List<UserRole> findAll() {
        return repository.findAll();
    }


    public Result<UserRole> add(UserRole userRole) {
        Result<UserRole> result = validate(userRole);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        UserRole p = repository.add(userRole);
        result.setPayload(p);
        return result;
    }


    public Result<UserRole> update(UserRole userRole) {
        Result<UserRole> result = validate(userRole);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        if (!repository.update(userRole)) {
            result.addMessage("userRole id " + userRole.getUserRoleId() + " not found", ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int userRoleId) {
        return repository.deleteById(userRoleId);
    }





    // ********************** validate ************************
    private Result<UserRole> validate(UserRole userRole) {
        Result<UserRole> result = new Result<>();

        if (userRole == null) {
            result.addMessage("userRole cannot be null", ResultType.INVALID);
            return result;
        }

        if (userRole.getRoleName() == null || userRole.getRoleName().trim().length() == 0) {
            result.addMessage("Role name is required", ResultType.INVALID);
        }


        for(UserRole sc : findAll()){
            if (userRole.getRoleName().trim().equalsIgnoreCase(sc.getRoleName())){
                result.addMessage("Role name already exists", ResultType.INVALID);
            }
        }


        return result;
    }

}
