package com.dev.esthomy.service;

import com.dev.esthomy.converter.OperationOfferConverter;
import com.dev.esthomy.dto.OperationOfferDto;
import com.dev.esthomy.dto.request.operationOfferRequest.CreateOperationOffer;
import com.dev.esthomy.models.Account;
import com.dev.esthomy.models.Hospital;
import com.dev.esthomy.models.OperationRequest;
import com.dev.esthomy.repository.OperationOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationOfferService {

    private final OperationOfferRepository operationOfferRepository;
    private final OperationOfferConverter operationOfferConverter;
    private final AccountService accountService;
    private final HospitalService hospitalService;
    private final OperationRequestService operationRequestService;

    public OperationOfferDto sendOffer(CreateOperationOffer createOperationOffer) {
        Account account = accountService.getUserByMail(createOperationOffer.getAccountEmail());
        Hospital hospital = hospitalService.getById(createOperationOffer.getHospitalId());
        OperationRequest operationRequest = operationRequestService.getById(createOperationOffer.getOperationRequestId());

        return operationOfferConverter.toDto(
                operationOfferRepository.save(
                        operationOfferConverter.toModel(createOperationOffer,account,operationRequest,hospital)));
    }
}
