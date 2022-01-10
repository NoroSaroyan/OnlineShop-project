package ru.gb.onlineshop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.onlineshop.entity.Role;
import ru.gb.onlineshop.entity.User;
import ru.gb.onlineshop.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User name " + username + " not found");
        }
        User u = user.get();
        return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(), getGrantedAuthorities(u));
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(User user) {
        logger.warn("trying to get granted for user : " + user.getEmail());
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            String rName = role.getName().toUpperCase(Locale.ROOT);
            if (rName.equals("ADMIN")) {
                logger.warn("role admin");
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else if (rName.equals("SUPER_ADMIN")) {
                logger.warn("role super admin");
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"));
            } else {
                logger.warn("role user");
                grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
            }
        }
        return grantedAuthorities;
    }
}