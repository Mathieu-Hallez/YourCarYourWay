package me.yourcaryourway.YourCarYourWay.repositories;

import me.yourcaryourway.YourCarYourWay.models.MessageType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MessageTypeRepository extends CrudRepository<MessageType, Long> {
    Optional<MessageType> findByName(String name);
}
