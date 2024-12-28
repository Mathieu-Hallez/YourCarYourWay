package me.yourcaryourway.YourCarYourWay.repositories;

import me.yourcaryourway.YourCarYourWay.models.Message;
import me.yourcaryourway.YourCarYourWay.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    Iterable<Message> findAllBySenderAndReceiver(User sender, User receiver);

    Optional<Message> findFirstBySenderIdAndReceiverIdOrderByCreatedAtDesc(Long senderId, Long receiverId);
}
