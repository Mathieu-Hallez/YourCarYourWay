package me.yourcaryourway.YourCarYourWay_WebAPI.services;

import me.yourcaryourway.YourCarYourWay_WebAPI.models.User;
import me.yourcaryourway.YourCarYourWay_WebAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
