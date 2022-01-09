package ru.gb.service;

import org.hibernate.internal.util.Cloneable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.gb.onlineshop.entity.User;
import ru.gb.onlineshop.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Component("userService")
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("\n_________try to find user : " + email);
        Optional<User> mayBeUser = userRepository.findByEmail(email);
        if (!mayBeUser.isPresent()) {
            throw new UsernameNotFoundException("User with username:" + email + "not found");
        }
        ru.gb.onlineshop.entity.User user = mayBeUser.get();
        UserDetails u = org.springframework.security.core.userdetails.User.
                withUsername(user.getEmail()).
                password(user.getPassword()).
                authorities("USER").
                build();
        return u;
    }

    public void login(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(token);

        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
            logger.debug(String.format("User %s logged in successfully!", username));
        } else {
            logger.error(String.format("Error with %s authentication!", username));
        }
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(
                pageable
        );
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    public User findById(Long id) {
        Optional<User> mayBeUser = userRepository.findById(id);
        return mayBeUser.orElse(null);
    }

    public void save(User user) {
        //TODO clone user object
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user != null) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("User with Email:" + user.get().getEmail() + "is not found");
        }
    }
}
