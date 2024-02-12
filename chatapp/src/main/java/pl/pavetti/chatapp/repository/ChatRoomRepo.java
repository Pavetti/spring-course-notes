package pl.pavetti.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pavetti.chatapp.model.ChatRoom;

import java.util.Optional;

public interface ChatRoomRepo extends JpaRepository<ChatRoom,String> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
