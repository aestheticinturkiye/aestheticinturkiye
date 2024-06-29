package com.dev.esthomy.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
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

        return response.getBody();
    }
}