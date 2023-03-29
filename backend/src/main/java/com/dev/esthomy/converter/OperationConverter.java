package com.dev.esthomy.converter;

import com.dev.esthomy.dto.OperationRequestDto;
import com.dev.esthomy.models.Hospital;
import com.dev.esthomy.models.OperationRequest;
import com.dev.esthomy.models.request.OperationRequestRequest;
import com.dev.esthomy.models.response.OperationRequestResponse;
import org.springframework.stereotype.Component;

@Component
public class OperationConverter {
    public OperationRequest toModel(OperationRequestRequest request) {
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

    public OperationRequestResponse toResponse(OperationRequestDto operationRequestDto)
    {
       return OperationRequestResponse.builder()
                .clientName(operationRequestDto.getClientName())
               .age(operationRequestDto.getAge())
                .build();
    }
}
