package com.dev.esthomy.controller;

import com.dev.esthomy.dto.request.operationOfferRequest.CreateOperationOffer;
import com.dev.esthomy.dto.request.operationRequest.CreateOperationRequest;
import com.dev.esthomy.dto.response.CreateOperationRequestResponse;
import com.dev.esthomy.service.OperationOfferService;
import com.dev.esthomy.service.OperationRequestService;
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

//    @PostMapping
//    public ResponseEntity<CreateOperationRequestResponse> create (@RequestBody CreateOperationOffer request) {
//
//    }
}
