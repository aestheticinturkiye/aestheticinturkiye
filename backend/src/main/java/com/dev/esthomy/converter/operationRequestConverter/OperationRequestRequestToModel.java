package com.dev.esthomy.converter.operationRequestConverter;

import com.dev.esthomy.models.OperationRequest;
import com.dev.esthomy.models.request.OperationRequestRequest;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class OperationRequestRequestToModel {
    public OperationRequest toModel(OperationRequestRequest request) {
        return OperationRequest.builder()
                .clientName(request.getClientName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .country(request.getCountry())
                .age(request.getAge())
                .build();
    }
}
