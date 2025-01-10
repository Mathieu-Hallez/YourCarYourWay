package me.yourcaryourway.YourCarYourWay_WebAPI.repositories;

import me.yourcaryourway.YourCarYourWay_WebAPI.models.Message;
import me.yourcaryourway.YourCarYourWay_WebAPI.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    Iterable<Message> findAllBySenderAndReceiver(User sender, User receiver);

    Optional<Message> findFirstBySenderIdAndReceiverIdOrderByCreatedAtDesc(Long senderId, Long receiverId);
}
