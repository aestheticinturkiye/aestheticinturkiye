package com.dev.esthomy.controller;

import com.dev.esthomy.models.request.OperationRequestRequest;
import com.dev.esthomy.models.response.OperationRequestResponse;
import com.dev.esthomy.service.OperationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation-request")
@RequiredArgsConstructor
public class OperationRequestController {
    private  final OperationRequestService operationRequestService;

//    @PostMapping()
//    public ResponseEntity<OperationRequestResponse> create (OperationRequestRequest request) {
//        return ResponseEntity.ok().body(operationRequestService.create(request));
//    }
}
