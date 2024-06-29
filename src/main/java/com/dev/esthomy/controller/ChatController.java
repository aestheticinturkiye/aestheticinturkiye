package com.dev.esthomy.controller;


import com.dev.esthomy.dto.ChatDto;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatService chatService;

    @GetMapping()
    public List<ChatDto> getAllChat(@AuthenticationPrincipal final JwtClaims principal) throws JsonProcessingException {
        return chatService.getAllChat(principal);
    }

}
