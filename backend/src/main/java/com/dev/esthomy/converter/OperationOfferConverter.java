package com.dev.esthomy.converter;

import com.dev.esthomy.dto.OperationOfferDto;
import com.dev.esthomy.dto.request.operationOfferRequest.CreateOperationOffer;
import com.dev.esthomy.dto.response.CreateOperationOfferResponse;
import com.dev.esthomy.models.Account;
import com.dev.esthomy.models.Hospital;
import com.dev.esthomy.models.OperationOffer;
import com.dev.esthomy.models.OperationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OperationOfferConverter {


    public OperationOffer toModel(CreateOperationOffer request, Account account, OperationRequest operationRequest, Hospital hospital) {
        return OperationOffer.builder()
                .price(request.getPrice())
                .description(request.getDescription())
                .operationRequest(operationRequest)
                .hospital(hospital)
                .account(account).build();
    }

    public OperationOfferDto toDto(OperationOffer operationOffer) {
        return OperationOfferDto.builder()
                .accountId(operationOffer.getAccount().getId())
                .hospitalId(operationOffer.getHospital().getId())
                .operationRequestId(operationOffer.getId())
                .build();
    }

    public CreateOperationOfferResponse toResponse(OperationOfferDto operationOfferDto) {
        return CreateOperationOfferResponse.builder()
                .operationId(operationOfferDto.getOperationRequestId())
                .build();
    }
}
