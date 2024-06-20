package com.dev.esthomy.service;

import com.dev.esthomy.constants.EmailTemplates;
import com.dev.esthomy.dto.request.CreateSubscriptionRequest;
import com.dev.esthomy.dto.response.CreateSubscriptionResponse;
import com.dev.esthomy.models.Subscription;
import com.dev.esthomy.repository.SubscriptionRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final MailService mailService;

    public CreateSubscriptionResponse create(final CreateSubscriptionRequest request) {
        final Subscription savedSubscription = subscriptionRepository.save(Subscription.builder()
                .email(request.getEmail())
                .build());

        log.info("Subscription created: {}", savedSubscription.getEmail());

        try {
            mailService.sendEmail(request.getEmail(), request.getEmail(), null, EmailTemplates.SUB_MAIL);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("Failed to send email. Error: " + e.getMessage());
        }


        return CreateSubscriptionResponse.builder()
                .id(savedSubscription.getId())
                .build();
    }
}
