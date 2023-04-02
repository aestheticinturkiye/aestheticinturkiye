package com.dev.esthomy.converter;

import com.dev.esthomy.dto.OperationRequestDto;
import com.dev.esthomy.models.OperationRequest;
import com.dev.esthomy.dto.request.CreateOperationRequest;
import com.dev.esthomy.dto.response.CreateOperationRequestResponse;
import org.springframework.stereotype.Component;

@Component
public class OperationConverter {
    public OperationRequest toModel(CreateOperationRequest request) {
        return OperationRequest.builder()
                .clientName(request.getClientName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .country(request.getCountry())
                .age(request.getAge())
                .build();
    }

    public OperationRequestDto toDto(OperationRequest operationRequest)
    {
        return OperationRequestDto.builder()
                .clientName(operationRequest.getClientName())
                .email(operationRequest.getEmail())
                .phoneNumber(operationRequest.getPhoneNumber())
                .country(operationRequest.getCountry())
                .age(operationRequest.getAge())
                .build();
    }

    public CreateOperationRequestResponse toResponse(OperationRequestDto operationRequestDto)
    {
       return CreateOperationRequestResponse.builder()
                .clientName(operationRequestDto.getClientName())
               .age(operationRequestDto.getAge())
                .build();
    }
}
