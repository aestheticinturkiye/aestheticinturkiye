package com.dev.esthomy.service;

import com.dev.esthomy.dto.request.CreateSubscriptionRequest;
import com.dev.esthomy.dto.response.CreateSubscriptionResponse;
import com.dev.esthomy.models.Subscription;
import com.dev.esthomy.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public CreateSubscriptionResponse create(final CreateSubscriptionRequest request) {
        final Subscription savedSubscription = subscriptionRepository.save(Subscription.builder()
                .email(request.getEmail())
                .build());

        log.info("Subscription created: {}", savedSubscription.getEmail());

        return CreateSubscriptionResponse.builder()
                .id(savedSubscription.getId())
                .build();
    }
}
