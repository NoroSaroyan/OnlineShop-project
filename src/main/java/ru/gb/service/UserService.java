package ru.gb.service;

import org.hibernate.internal.util.Cloneable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.gb.onlineshop.entity.User;
import ru.gb.onlineshop.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Component("userService")
public class UserService {

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    //    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("\n_________try to find user : " + email);
        User mayBeUser = userRepository.findByEmail(email);
        if (mayBeUser == null) {
            throw new UsernameNotFoundException("User with username:" + email + "not found");
        }
        ru.gb.onlineshop.entity.User user = mayBeUser;
        UserDetails u = org.springframework.security.core.userdetails.User.
                withUsername(user.getEmail()).
                password(user.getPassword()).
                authorities("USER").
                build();
        return u;
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
}
