package me.yourcaryourway.YourCarYourWay.services;

import me.yourcaryourway.YourCarYourWay.models.User;
import me.yourcaryourway.YourCarYourWay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(final Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User getUser(final String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    public boolean existsByEmail(final String email) {
        return this.userRepository.existsByEmail(email);
    }
}
