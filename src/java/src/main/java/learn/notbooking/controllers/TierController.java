package learn.notbooking.controllers;


import learn.notbooking.domain.Result;
import learn.notbooking.domain.ResultType;
import learn.notbooking.domain.TierService;
import learn.notbooking.models.Tier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/tier")
public class TierController {

    private final TierService service;

    public TierController(TierService service) {
        this.service = service;
    }

    @GetMapping
    public List<Tier> findAll() {

        return service.findAll();

    }

    @GetMapping("/{tierId}")
    public ResponseEntity<Tier> findById(@PathVariable int tierId) {
        Tier tier = service.findById(tierId);
        try{
            if (tier == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(tier, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Tier> add(@RequestBody Tier tier) {
        Result<Tier> result = service.add(tier);
        if (result.getType() == ResultType.INVALID) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{tierId}")
    public ResponseEntity<Void> update(@PathVariable int tierId, @RequestBody Tier tier) {

        // id conflict. stop immediately.
        if (tierId != tier.getTierId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 4. ResultType -> HttpStatus
        try {
            Result<Tier> result = service.update(tier);
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

    @DeleteMapping("/{tierId}")
    public ResponseEntity<Void> delete(@PathVariable int tierId) {
        try{
            if (service.deleteById(tierId)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
