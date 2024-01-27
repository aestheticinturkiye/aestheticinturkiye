package com.dev.esthomy.controller;

import com.dev.esthomy.converter.OperationConverter;
import com.dev.esthomy.dto.request.CreateOperationRequest;
import com.dev.esthomy.dto.response.CreateOperationRequestResponse;
import com.dev.esthomy.service.OperationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/operation-request")
@RequiredArgsConstructor
public class OperationRequestController {
    private final OperationRequestService operationRequestService;

    private final OperationConverter operationConverter;

    @PostMapping
   public ResponseEntity<CreateOperationRequestResponse> create (@RequestBody CreateOperationRequest request) {
      return ResponseEntity.ok().body(
              operationConverter.toResponse(
                      operationRequestService.create(request)));
   }
}
