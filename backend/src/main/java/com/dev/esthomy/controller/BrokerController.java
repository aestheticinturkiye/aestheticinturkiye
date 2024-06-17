package com.dev.esthomy.controller;

import com.dev.esthomy.dto.request.CreateBrokerRequest;
import com.dev.esthomy.dto.request.GetBrokerResponse;
import com.dev.esthomy.dto.response.CreateBrokerResponse;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.service.BrokerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/broker")
@RequiredArgsConstructor
@Slf4j
public class BrokerController {
    private final BrokerService brokerService;

    @PostMapping
    public ResponseEntity<CreateBrokerResponse> create(final @Valid @RequestBody CreateBrokerRequest request) {
        log.info(String.valueOf(request));
        return ResponseEntity.ok().body(brokerService.create(request));
    }

    @GetMapping
    public ResponseEntity<GetBrokerResponse> getBroker(final JwtClaims principal) {
        return ResponseEntity.ok().body(brokerService.getBroker(principal));
    }
}
