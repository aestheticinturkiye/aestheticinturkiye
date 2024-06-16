package com.dev.esthomy.controller;

import com.dev.esthomy.dto.request.CreateFindPartnerRequest;
import com.dev.esthomy.dto.response.CreateFindPartnerRequestResponse;
import com.dev.esthomy.dto.response.GetFindPartnerRequestsResponse;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.service.FindPartnerRequestService;
import com.dev.esthomy.util.ObjectConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/find-partner-request")
@RequiredArgsConstructor
public class FindPartnerRequestController {
    private final FindPartnerRequestService findPartnerRequestService;
    private final ObjectConverter objectConverter;

    @PostMapping
    public ResponseEntity<CreateFindPartnerRequestResponse> create(@RequestPart("partnerReq") String request,
                                                                   @AuthenticationPrincipal JwtClaims principal,@RequestPart("files") List<MultipartFile> files) {
        CreateFindPartnerRequest createFindPartnerRequest = objectConverter.convert(request);
        return ResponseEntity.ok().body(findPartnerRequestService.create(principal, createFindPartnerRequest,files));
    }


    @GetMapping
    public ResponseEntity<GetFindPartnerRequestsResponse> get(@AuthenticationPrincipal JwtClaims principal) {
        return ResponseEntity.ok().body(findPartnerRequestService.get(principal));
    }

    @GetMapping("/all")
    public ResponseEntity<GetFindPartnerRequestsResponse> getALL(@AuthenticationPrincipal JwtClaims principal) {
        return ResponseEntity.ok().body(findPartnerRequestService.getAll(principal));
    }
}