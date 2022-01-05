package learn.notbooking.controllers;


import learn.notbooking.domain.Result;
import learn.notbooking.domain.ResultType;
import learn.notbooking.domain.UserService;
import learn.notbooking.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> findAll() {

        return service.findAll();

    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findById(@PathVariable int userId) {
        User user = service.findById(userId);
        try{
            if (user == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<User> add(@RequestBody User user) {
        Result<User> result = service.add(user);
        if (result.getType() == ResultType.INVALID) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> update(@PathVariable int userId, @RequestBody User user) {

        // id conflict. stop immediately.
        if (userId != user.getUserId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 4. ResultType -> HttpStatus
        try {
            Result<User> result = service.update(user);
            if (result.getType() == ResultType.INVALID) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else if (result.getType() == ResultType.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable int userId) {
        try{
            if (service.deleteById(userId)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
