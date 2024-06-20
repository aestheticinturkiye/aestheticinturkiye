package com.dev.esthomy.dto.request;

import com.dev.esthomy.validator.aspect.ValidateEmail;
import lombok.Data;

@Data
public class CreateSubscriptionRequest {
    @ValidateEmail
    private String email;
}
