package com.dev.esthomy.controller;

import com.dev.esthomy.dto.request.CreateProposalRequest;
import com.dev.esthomy.dto.response.CreateProposalResponse;
import com.dev.esthomy.dto.response.GetAllProposalsResponse;
import com.dev.esthomy.dto.response.GetProposalResponse;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.models.enums.MemberRole;
import com.dev.esthomy.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/proposal")
@RequiredArgsConstructor
public class ProposalController {
    private final ProposalService proposalService;

    @PostMapping
    public ResponseEntity<CreateProposalResponse> create(@RequestBody final CreateProposalRequest request, @AuthenticationPrincipal JwtClaims principal) {
        return ResponseEntity.ok().body(proposalService.create(principal, request));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetProposalResponse> get(@RequestHeader("member-id") final String memberId,
                                                   @RequestHeader("member-role") final MemberRole role,
                                                   @PathVariable("id") final String id) {
        return ResponseEntity.ok().body(proposalService.get(role, memberId, id));
    }

    @GetMapping
    public ResponseEntity<GetAllProposalsResponse> getAll(@RequestHeader("member-id") final String memberId,
                                                          @RequestHeader("member-role") final MemberRole role) {
        return ResponseEntity.ok().body(proposalService.getAll(memberId, role));
    }

}
