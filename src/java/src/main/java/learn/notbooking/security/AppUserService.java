package learn.notbooking.security;

import learn.notbooking.data.UserJdbcTemplateRepository;
import learn.notbooking.data.UserRepository;
import learn.notbooking.models.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
public class AppUserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public AppUserService(UserRepository repository,PasswordEncoder encoder){
        this.repository= repository;
        this.encoder=encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        AppUser appUser = repository.findByUsername(username);

        if(appUser == null || !appUser.isEnabled()){
            throw new UsernameNotFoundException(username+" not found" + appUser.isEnabled() + appUser);
        }

        return appUser;
    }

    public AppUser create(String username, String password, int userRoleId){
        validate(username);
        validatePassword(password);
        //int userRoleId = 2;
        password = encoder.encode(password);

        AppUser appUser = new AppUser(0,username,password,userRoleId,false);

        return repository.create(appUser);
    }

    private void validate(String username){
        if(username == null || username.isBlank()){
            throw new ValidationException("username is required");
        }

        if(username.length()>50){
            throw new ValidationException("username must be less than 50 characters");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 8) {
            throw new ValidationException("password must be at least 8 characters");
        }

        int digits = 0;
        int letters = 0;
        int others = 0;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                digits++;
            } else if (Character.isLetter(c)) {
                letters++;
            } else {
                others++;
            }
        }

        if (digits == 0 || letters == 0 || others == 0) {
            throw new ValidationException("password must contain a digit, a letter, and a non-digit/non-letter");
        }
    }
}
