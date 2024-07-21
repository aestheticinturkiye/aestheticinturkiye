package com.dev.esthomy.controller;

import com.dev.esthomy.dto.OperationDto;
import com.dev.esthomy.dto.request.CreateOperationRequest;
import com.dev.esthomy.dto.response.CreateOperationResponse;
import com.dev.esthomy.models.enums.MemberRole;
import com.dev.esthomy.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/operation")
@RequiredArgsConstructor
public class OperationController {
    private final OperationService operationService;

    @PostMapping
    public ResponseEntity<CreateOperationResponse> create(@RequestBody final CreateOperationRequest request,
                                                          @RequestHeader("member-role") final MemberRole role) {
        return ResponseEntity.ok().body(operationService.create(request, role));
    }

    @GetMapping
    public ResponseEntity<Optional<OperationDto>> get(@RequestHeader("operation-id") final String operationId) {
        return ResponseEntity.ok().body(operationService.getByOperationId(operationId));
    }
}
