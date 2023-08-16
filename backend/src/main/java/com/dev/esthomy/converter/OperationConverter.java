package com.dev.esthomy.converter;

import com.dev.esthomy.dto.OperationRequestDto;
import com.dev.esthomy.models.Account;
import com.dev.esthomy.models.OperationRequest;
import com.dev.esthomy.dto.request.operationRequest.CreateOperationRequest;
import com.dev.esthomy.dto.response.CreateOperationRequestResponse;
import org.springframework.stereotype.Component;

@Component
public class OperationConverter {
    public OperationRequest toModel(CreateOperationRequest request , Account userAccount) {
        return OperationRequest.builder()
                .userAccount(userAccount)
                .aestheticType(request.getAestheticType())
                .build();
    }

    public OperationRequestDto toDto(OperationRequest operationRequest,Account account)
    {
        return OperationRequestDto.builder()
                .userAccount(account)
                .aestheticType(operationRequest.getAestheticType())
                .build();
    }

    public CreateOperationRequestResponse toResponse(OperationRequestDto operationRequestDto)
    {
       return CreateOperationRequestResponse.builder()
               .userAccount(operationRequestDto.getUserAccount())
               .aestheticType(operationRequestDto.getAestheticType())
                .build();
    }
}
