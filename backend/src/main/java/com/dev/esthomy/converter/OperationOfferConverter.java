package com.dev.esthomy.converter;

import com.dev.esthomy.dto.OperationOfferDto;
import com.dev.esthomy.dto.response.CreateOperationOfferResponse;
import com.dev.esthomy.models.OperationOffer;
import org.springframework.stereotype.Component;

@Component
public class OperationOfferConverter {


    public OperationOfferDto toDto(OperationOffer operationOffer){
        return OperationOfferDto.builder()
                .accountId(operationOffer.getAccount().getId())
                .hospitalId(operationOffer.getHospital().getId())
                .operationRequestId(operationOffer.getId())
                .build();
    }

    public CreateOperationOfferResponse toResponse(OperationOfferDto operationOfferDto){
        return CreateOperationOfferResponse.builder()
                .operationId(operationOfferDto.getOperationRequestId())
                .build();
    }
}
