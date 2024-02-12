package pl.pavetti.chatapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.pavetti.chatapp.model.UserEntity;
import pl.pavetti.chatapp.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public UserEntity addUser(
            @Payload UserEntity user
    ){
        service.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    public UserEntity disconnectUser(
            @Payload UserEntity user
    ){
        service.disconntectUser(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> findConnectedUser(){
        return ResponseEntity.ok(service.findConnectedUsers());
    }

}
