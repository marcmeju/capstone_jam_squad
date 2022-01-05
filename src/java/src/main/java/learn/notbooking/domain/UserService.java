package learn.notbooking.domain;

import learn.notbooking.data.UserRepository;
import learn.notbooking.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public User findById(int userId){return repository.findById(userId);}


    public List<User> findAll() {
        return repository.findAll();
    }


    public Result<User> add(User user) {
        Result<User> result = validate(user);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        User p = repository.add(user);
        result.setPayload(p);
        return result;
    }


    public Result<User> update(User user) {
        Result<User> result = validate(user);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        if (!repository.update(user)) {
            result.addMessage("user id " + user.getUserId() + " not found", ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int userId) {
        return repository.deleteById(userId);
    }





    // ********************** validate ************************
    private Result<User> validate(User user) {
        Result<User> result = new Result<>();

        if (user == null) {
            result.addMessage("user cannot be null", ResultType.INVALID);
            return result;
        }

        if (user.getUserName() == null || user.getUserName().trim().length() == 0) {
            result.addMessage("Username is required", ResultType.INVALID);
        }

        if (user.getPasswordHash() == null || user.getPasswordHash().trim().length() == 0) {
            result.addMessage("Password is required", ResultType.INVALID);
        }

        for(User sc : findAll()){
            if (user.getUserName().trim().equalsIgnoreCase(sc.getUserName())){
                result.addMessage("Username already exists", ResultType.INVALID);
            }
        }


        return result;
    }

}
