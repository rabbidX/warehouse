package dmitry.garyanov.warehouse.conf;

import dmitry.garyanov.warehouse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() {

        List<dmitry.garyanov.warehouse.model.User> users = userService.getAll();
        int arraySize = users.size();
        UserDetails[] userDetails = new UserDetails[users.size()];
        for (int i = 0; i < arraySize; i++) {
            dmitry.garyanov.warehouse.model.User user = users.get(i);
            userDetails[i] = User.builder()
                    .username(user.getName())
                    .password(passwordEncoder().encode(user.getPassword()))
                    .roles("ADMIN")
                    .build();
        }
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .anyRequest()
                .authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }


}
