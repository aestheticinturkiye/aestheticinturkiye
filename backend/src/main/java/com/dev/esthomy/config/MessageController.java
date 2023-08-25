package com.dev.esthomy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message") //app/message
    @SendTo("/chatroom/public")
    private Message receivePubligMessage(@Payload Message message)
    {
        return message;
    }

    @MessageMapping("/private-message")
    public Message receivePrivateMessage(@Payload Message message)
    {
        simpMessagingTemplate.convertAndSendToUser(message.getRecevierName(),"private",message);
        return message;
    }
}
