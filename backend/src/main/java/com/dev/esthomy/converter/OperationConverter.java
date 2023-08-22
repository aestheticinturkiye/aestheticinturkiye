package com.dev.esthomy.converter;

import com.dev.esthomy.dto.OperationRequestDto;
import com.dev.esthomy.dto.request.operationRequest.CreateOperationRequest;
import com.dev.esthomy.dto.response.CreateOperationRequestResponse;
import com.dev.esthomy.dto.response.OperationRequestListResponse;
import com.dev.esthomy.models.Account;
import com.dev.esthomy.models.OperationOffer;
import com.dev.esthomy.models.OperationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OperationConverter {

    private final OperationOfferConverter operationOfferConverter;

    public OperationRequest toModel(CreateOperationRequest request, Account account) {
        return OperationRequest.builder().aestheticType(request.getAestheticType()).userAccount(account).build();
    }

    public OperationRequestDto toDto(OperationRequest operationRequest) {
        return OperationRequestDto.builder().aestheticType(operationRequest.getAestheticType()).userAccount(operationRequest.getUserAccount()).operationOfferList(operationRequest.getOperationOfferList()).build();
    }

    public CreateOperationRequestResponse toResponse(OperationRequestDto operationRequestDto) {
        List<OperationOffer> operationOfferList = new ArrayList<>();

        if (operationRequestDto.getOperationOfferList() != null) {
            operationOfferList = operationRequestDto.getOperationOfferList();
        }
        return CreateOperationRequestResponse.builder().userId(operationRequestDto.getUserAccount().getId()).aestheticType(operationRequestDto.getAestheticType()).operationOfferList(operationOfferList).build();
    }

    public OperationRequestListResponse toList(List<OperationRequest> fromModel) {
        return OperationRequestListResponse.
                builder()
                .operationRequestDtos(fromModel.stream().map(this::toDto).collect(Collectors.toList())).build();
    }
}
