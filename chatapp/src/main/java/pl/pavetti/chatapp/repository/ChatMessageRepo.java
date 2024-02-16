package pl.pavetti.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pavetti.chatapp.model.ChatMessage;

import java.util.List;

public interface ChatMessageRepo extends JpaRepository<ChatMessage,String> {

    List<ChatMessage> findByChatId(String id);
}
