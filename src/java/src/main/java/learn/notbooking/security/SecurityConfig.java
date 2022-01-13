package learn.notbooking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter){
        this.converter = converter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //needed when not using html forms so I was unsure
        http.csrf().disable();
        http.cors();

        //write proper urls, being able to add where needed
        http.authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/create_account").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/booking").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/booking/*").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/booking/customer/*").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/contact").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/contact/*").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/customer").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/customer/*").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/customer/find/*").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/event").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/event/*").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/location").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/location/*").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/package").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/package/*").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/package/*/details").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/tier/*").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/tier/").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/user/*").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/user/").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/userRole/").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/userRole/*").permitAll()
                .antMatchers(HttpMethod.POST,
                        "/booking").permitAll()
                .antMatchers(HttpMethod.POST,
                        "/contact").permitAll()
                .antMatchers(HttpMethod.POST,
                        "/customer").permitAll()
                .antMatchers(HttpMethod.POST,
                        "/event").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/location").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/package").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/tier").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/user").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/userRole").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/booking/*").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/contact/*").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/customer/*").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/event/*").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/location/*").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/package/*").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/tier/*").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/user/*").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/userRole/*").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/booking/*").permitAll()
                .antMatchers(HttpMethod.DELETE,
                        "/contact/*").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/customer/*").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/event/*").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/location/*").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/package/*").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/tier/*").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/user/*").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/userRole/*").hasAnyRole("ADMIN")

                .antMatchers("/**").denyAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Autowired
    private PasswordEncoder encoder;

//    @Bean
//    @Lazy
//    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
//        return new HandlerMappingIntrospector();
//    }
// Takeout later
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        var userBuilder = User.withUsername("user")
//                .password("user").passwordEncoder(password -> encoder.encode(password))
//                .roles("USER");
//
//        var adminBuilder = User.withUsername("admin")
//                .password("admin").passwordEncoder(password -> encoder.encode(password))
//                .roles("ADMIN");
//
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder)
//                .withUser(adminBuilder);
//    }



    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }
}
