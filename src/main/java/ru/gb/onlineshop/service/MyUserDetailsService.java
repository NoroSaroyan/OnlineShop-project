package ru.gb.onlineshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.onlineshop.entity.User;
import ru.gb.onlineshop.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

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
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
       boolean exists = user.getRoles()
                .stream()
                .anyMatch(item -> item.getName().equals("ADMIN"));

        if (exists) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else{
            exists = user.getRoles().
                    stream().
                    anyMatch(item-> item.getName().equals("SUPER_ADMIN"));
            if (exists){
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"));
            }
        }
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        return grantedAuthorities;
    }
}