package learn.notbooking.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AppUser extends User {
    private int userId;
    private String userName;
    private String passwordHash;
    private int userRoleId;

    private static final String AUTHORITY_PREFIX = "ROLE_";
    List<String> roles;


//    public AppUser() {
//        super();
//
//    }
//
//    public AppUser(int userId, String userName, String passwordHash, int userRoleId) {
//        super();
//
//        this.userId = userId;
//        this.userName = userName;
//        this.passwordHash = passwordHash;
//        this.userRoleId = userRoleId;
//    }

    public AppUser(int userId, String userName, String passwordHash,  int userRoleId, boolean disabled,  List<String> roles) {

        super(userName, passwordHash, !disabled,
                true, true, true,
                convertRolesToAuthorities(roles));

        this.userId = userId;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.userRoleId = userRoleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public static List<GrantedAuthority> convertRolesToAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (String role : roles) {
            Assert.isTrue(!role.startsWith(AUTHORITY_PREFIX),
                    () ->
                            String.
                                    format("%s cannot start with %s (it is automatically added)",
                                            role, AUTHORITY_PREFIX));
            authorities.add(new SimpleGrantedAuthority(AUTHORITY_PREFIX + role));
        }
        return authorities;
    }

    public static List<String> convertAuthoritiesToRoles(Collection<GrantedAuthority> authorities) {
        return authorities.stream()
                .map(a -> a.getAuthority().substring(AUTHORITY_PREFIX.length()))
                .collect(Collectors.toList());
    }

    private List<String> convertIdToRoles(int userRoleId){
        if( userRoleId == 1){
            return  roles = List.of("guest");
            }
        if( userRoleId == 2){
            return  roles = List.of("member");
        }
        if( userRoleId == 3){
            return  roles = List.of("vip");
        }
        if( userRoleId == 4){
            return  roles = List.of("admin");
        }
        return List.of("Invalid user type");

    }
}
