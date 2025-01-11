package me.yourcaryourway.YourCarYourWay_WebAPI.repositories;

import me.yourcaryourway.YourCarYourWay_WebAPI.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
