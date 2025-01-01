package me.yourcaryourway.YourCarYourWay.services;

import me.yourcaryourway.YourCarYourWay.models.User;
import me.yourcaryourway.YourCarYourWay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return StreamSupport.stream(this.userRepository.findAll().spliterator(), false).toList();
    }

    public User getUser(final String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }
}
