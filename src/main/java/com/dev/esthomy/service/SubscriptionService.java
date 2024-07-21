package com.dev.esthomy.service;

import com.dev.esthomy.constants.EmailTemplates;
import com.dev.esthomy.dto.request.CreateSubscriptionRequest;
import com.dev.esthomy.dto.request.UpdateSubscriptionRequest;
import com.dev.esthomy.dto.response.CreateSubscriptionResponse;
import com.dev.esthomy.dto.response.UpdateSubscriptionResponse;
import com.dev.esthomy.exception.BusinessException;
import com.dev.esthomy.models.Subscription;
import com.dev.esthomy.models.enums.AestheticType;
import com.dev.esthomy.repository.SubscriptionRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import static com.dev.esthomy.exception.error.BusinessError.SUBSCRIPTION_NOT_FOUND;

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

    public UpdateSubscriptionResponse update(final UpdateSubscriptionRequest request, final UUID id) {

        final Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new BusinessException(SUBSCRIPTION_NOT_FOUND));

        subscription.setAestheticType(AestheticType.fromValue(request.getType()));

        final Subscription updatedSubscription = subscriptionRepository.save(subscription);

        log.info("Subscription updated: {}", updatedSubscription.getEmail());

        return UpdateSubscriptionResponse.builder()
                .id(updatedSubscription.getId())
                .build();
    }
}
