package com.dev.esthomy.converter;

import com.dev.esthomy.dto.OperationRequestDto;
import com.dev.esthomy.dto.request.operationRequest.CreateOperationRequest;
import com.dev.esthomy.dto.response.CreateOperationRequestResponse;
import com.dev.esthomy.models.Account;
import com.dev.esthomy.models.OperationRequest;
import com.dev.esthomy.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OperationConverter {
    public OperationRequest toModel(CreateOperationRequest request, Account account) {
        return OperationRequest.builder()
                .aestheticType(request.getAestheticType())
                .userAccount(account)
                .build();
    }

    public OperationRequestDto toDto(OperationRequest operationRequest) {
        return OperationRequestDto.builder()
                .aestheticType(operationRequest.getAestheticType())
                .build();
    }

    public CreateOperationRequestResponse toResponse(OperationRequestDto operationRequestDto) {
        return CreateOperationRequestResponse.builder()
                .userAccount(operationRequestDto.getUserAccount())
                .aestheticType(operationRequestDto.getAestheticType())
                .build();
    }
}
