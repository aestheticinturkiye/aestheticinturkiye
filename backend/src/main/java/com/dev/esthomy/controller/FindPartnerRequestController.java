package com.dev.esthomy.controller;

import com.dev.esthomy.dto.request.CreateFindPartnerRequest;
import com.dev.esthomy.dto.response.CreateFindPartnerRequestResponse;
import com.dev.esthomy.dto.response.GetFindPartnerRequestsResponse;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.service.FindPartnerRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/find-partner-request")
@RequiredArgsConstructor
public class FindPartnerRequestController {
    private final FindPartnerRequestService findPartnerRequestService;

    @PostMapping
    public ResponseEntity<CreateFindPartnerRequestResponse> create(@RequestBody CreateFindPartnerRequest request,
                                                                   @AuthenticationPrincipal JwtClaims principal) {
        return ResponseEntity.ok().body(findPartnerRequestService.create(principal, request));
    }

    @GetMapping
    public ResponseEntity<GetFindPartnerRequestsResponse> get(@AuthenticationPrincipal JwtClaims principal) {
        return ResponseEntity.ok().body(findPartnerRequestService.get(principal));
    }
}
