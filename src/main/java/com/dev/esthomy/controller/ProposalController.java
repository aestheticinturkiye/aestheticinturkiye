package com.dev.esthomy.controller;

import com.dev.esthomy.dto.request.CreateProposalRequest;
import com.dev.esthomy.dto.response.CreateProposalResponse;
import com.dev.esthomy.dto.response.GetAllProposalsResponse;
import com.dev.esthomy.dto.response.GetProposalResponse;
import com.dev.esthomy.models.enums.MemberRole;
import com.dev.esthomy.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/proposal")
@RequiredArgsConstructor
public class ProposalController {
    private final ProposalService proposalService;

    @PostMapping
    public ResponseEntity<CreateProposalResponse> create(@RequestBody final CreateProposalRequest request,
                                                         @RequestHeader("member-id") final UUID memberId,
                                                         @RequestHeader("member-role") final MemberRole role
    ) {
        return ResponseEntity.ok().body(proposalService.create(request, memberId, role));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetProposalResponse> get(@PathVariable("id") final UUID id) {
        return ResponseEntity.ok().body(proposalService.get(id));
    }

    @GetMapping
    public ResponseEntity<GetAllProposalsResponse> getAll(@RequestHeader("member-id") final UUID memberId,
                                                          @RequestHeader("member-role") final MemberRole role) {
        return ResponseEntity.ok().body(proposalService.getAll(memberId, role));
    }

}
