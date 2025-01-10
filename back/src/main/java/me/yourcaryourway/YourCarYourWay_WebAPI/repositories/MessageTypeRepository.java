package me.yourcaryourway.YourCarYourWay_WebAPI.repositories;

import me.yourcaryourway.YourCarYourWay_WebAPI.models.MessageType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MessageTypeRepository extends CrudRepository<MessageType, Long> {
    Optional<MessageType> findByName(String name);
}
