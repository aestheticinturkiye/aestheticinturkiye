package com.dev.esthomy.controller;

import com.dev.esthomy.dto.request.CreateClientRequest;
import com.dev.esthomy.dto.response.CreateClientResponse;
import com.dev.esthomy.dto.response.GetClientResponse;
import com.dev.esthomy.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/client")
@RequiredArgsConstructor
@Slf4j
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<CreateClientResponse> create(final @Valid @RequestBody CreateClientRequest request) {
        log.info(String.valueOf(request));
        return ResponseEntity.ok().body(clientService.create(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetClientResponse> getClient(@PathVariable final String id) {
        return ResponseEntity.ok().body(clientService.getClient(id));
    }
}
