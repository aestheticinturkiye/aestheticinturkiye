package com.dev.esthomy.service;

import com.dev.esthomy.converter.OperationOfferConverter;
import com.dev.esthomy.dto.OperationOfferDto;
import com.dev.esthomy.dto.request.operationOfferRequest.CreateOperationOffer;
import com.dev.esthomy.repository.OperationOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationOfferService {

    private final OperationOfferRepository operationOfferRepository;
    private final OperationOfferConverter operationOfferConverter;

//    public OperationOfferDto sendOffer(CreateOperationOffer createOperationOffer) {
//        return operationOfferConverter.toDto(
//                operationOfferRepository.save(
//                        operationOfferConverter.toModel(createOperationOffer)));
//    }
}
