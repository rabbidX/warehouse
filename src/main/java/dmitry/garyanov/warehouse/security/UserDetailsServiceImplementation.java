package dmitry.garyanov.warehouse.security;

import dmitry.garyanov.warehouse.model.User;
import dmitry.garyanov.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserDetailsServiceImplementation")
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImplementation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByName(username);

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),user.getAuthorities());
    }
}
