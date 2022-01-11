package learn.notbooking.data;

import learn.notbooking.models.AppUser;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface UserRepository {
    List<AppUser> findAll();
    AppUser findById(int userId);

    @Transactional
    AppUser findByUsername(String username);

    @Transactional
    AppUser create(AppUser user);

    @Transactional
    boolean update(AppUser user);


    AppUser add(AppUser user);

//    boolean update(AppUser user);



    boolean deleteById(int userId);
}
