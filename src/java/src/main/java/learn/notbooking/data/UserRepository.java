package learn.notbooking.data;

import learn.notbooking.models.AppUser;


import java.util.List;

public interface UserRepository {
    List<AppUser> findAll();
    AppUser findById(int userId);

    AppUser add(AppUser user);

    boolean update(AppUser user);



    boolean deleteById(int userId);
}
