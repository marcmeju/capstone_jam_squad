package learn.notbooking.data;

import learn.notbooking.models.UserRole;

import java.util.List;

public interface UserRoleRepository {
    List<UserRole> findAll();
    UserRole findById(int userRoleId);

    UserRole add(UserRole userRole);

    boolean update(UserRole userRole);

    boolean deleteById(int userRoleId);
}
