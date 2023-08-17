package com.dev.esthomy.controller;

import com.dev.esthomy.converter.OperationOfferConverter;
import com.dev.esthomy.dto.request.operationOfferRequest.CreateOperationOffer;
import com.dev.esthomy.dto.response.CreateOperationOfferResponse;
import com.dev.esthomy.service.OperationOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/operation-offer")
@RequiredArgsConstructor
public class OperationOfferController {

    private final OperationOfferService operationOfferService;
    private final OperationOfferConverter operationOfferConverter;

//    @PostMapping
//    public ResponseEntity<CreateOperationOfferResponse> sendOffer(@RequestBody CreateOperationOffer createOperationOffer) {
//        return ResponseEntity.ok(operationOfferConverter.toResponse(operationOfferService.sendOffer(createOperationOffer)));
//    }
}
