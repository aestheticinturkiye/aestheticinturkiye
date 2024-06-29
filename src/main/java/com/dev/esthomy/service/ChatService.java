package com.dev.esthomy.service;

import com.dev.esthomy.dto.ChatDto;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ChatService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.chatServiceUrl}")
    private String chatServiceUrl;

    public String createChat(String brokerId, String clientId, String proposalId) {
        String jsonInputString = String.format("{\"brokerId\": \"%s\", \"clientId\": \"%s\", \"proposalId\": \"%s\"}", brokerId, clientId, proposalId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(jsonInputString, headers);

        ResponseEntity<String> response = restTemplate.exchange(chatServiceUrl, HttpMethod.POST, request, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println("Response Body: " + response.getBody());
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            String chatId = rootNode.path("_id").asText();
            return chatId;
        } catch (Exception e) {
            System.err.println("Failed to parse response body: " + response.getBody());
            e.printStackTrace();
            throw new RuntimeException("Failed to parse chat response", e);
        }
    }

    public List<ChatDto> getAllChat(JwtClaims principal) throws JsonProcessingException {
        String userId = principal.getId();
        String url = "http://localhost:4000/api/chat?userId=" + userId;

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Yanıtı String olarak al
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        List<ChatDto> chats = objectMapper.readValue(response.getBody(), new TypeReference<List<ChatDto>>(){});

        log.info(chats.toString());
        System.out.println(chats);
        return chats;
    }
}
