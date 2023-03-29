package com.dev.esthomy.converter.operationRequestConverter;

import com.dev.esthomy.dto.OperationRequestDto;
import com.dev.esthomy.models.response.OperationRequestResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationRequestDtoToResponse {

    public OperationRequestResponse toResponse(OperationRequestDto request) {
        return OperationRequestResponse.builder()
                .clientName(request.getClientName())
                .age(request.getAge())
                .build();
    }
}
