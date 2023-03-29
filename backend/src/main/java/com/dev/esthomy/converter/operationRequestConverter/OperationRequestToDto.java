package com.dev.esthomy.converter.operationRequestConverter;

import com.dev.esthomy.dto.OperationRequestDto;
import com.dev.esthomy.models.OperationRequest;
import org.springframework.stereotype.Component;

@Component
public class OperationRequestToDto {
    public OperationRequestDto fromModel(OperationRequest operationRequest) {
        return OperationRequestDto.builder()
                .clientName(operationRequest.getClientName())
                .email(operationRequest.getEmail())
                .phoneNumber(operationRequest.getPhoneNumber())
                .country(operationRequest.getCountry())
                .age(operationRequest.getAge())
                .build();
    }
}
