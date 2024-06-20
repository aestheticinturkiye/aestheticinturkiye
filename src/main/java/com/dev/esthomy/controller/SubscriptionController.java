package com.dev.esthomy.controller;

import com.dev.esthomy.dto.request.CreateSubscriptionRequest;
import com.dev.esthomy.dto.response.CreateSubscriptionResponse;
import com.dev.esthomy.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/subscribe")
@Slf4j
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<CreateSubscriptionResponse> create(@RequestBody final CreateSubscriptionRequest request) {
        log.info(request.toString());
        return ResponseEntity.ok().body(subscriptionService.create(request));
    }
}
