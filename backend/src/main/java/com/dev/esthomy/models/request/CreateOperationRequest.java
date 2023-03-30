package com.dev.esthomy.models.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOperationRequest {
    private String clientName;
    private String email;
    private String phoneNumber;
    private String country;
    private int age;

}
