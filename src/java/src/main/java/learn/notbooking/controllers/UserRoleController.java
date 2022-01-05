package learn.notbooking.controllers;


import learn.notbooking.domain.Result;
import learn.notbooking.domain.ResultType;
import learn.notbooking.domain.UserRoleService;
import learn.notbooking.models.UserRole;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/userRole")
public class UserRoleController {

    private final UserRoleService service;

    public UserRoleController(UserRoleService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserRole> findAll() {

        return service.findAll();

    }

    @GetMapping("/{userRoleId}")
    public ResponseEntity<UserRole> findById(@PathVariable int userRoleId) {
        UserRole userRole = service.findById(userRoleId);
        try{
            if (userRole == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(userRole, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<UserRole> add(@RequestBody UserRole userRole) {
        Result<UserRole> result = service.add(userRole);
        if (result.getType() == ResultType.INVALID) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{userRoleId}")
    public ResponseEntity<Void> update(@PathVariable int userRoleId, @RequestBody UserRole userRole) {

        // id conflict. stop immediately.
        if (userRoleId != userRole.getUserRoleId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 4. ResultType -> HttpStatus
        try {
            Result<UserRole> result = service.update(userRole);
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

    @DeleteMapping("/{userRoleId}")
    public ResponseEntity<Void> delete(@PathVariable int userRoleId) {
        try{
            if (service.deleteById(userRoleId)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
