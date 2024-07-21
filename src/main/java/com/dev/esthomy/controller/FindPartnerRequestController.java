package com.dev.esthomy.controller;

import com.dev.esthomy.dto.FindPartnerRequestDto;
import com.dev.esthomy.dto.request.CreateFindPartnerRequest;
import com.dev.esthomy.dto.response.CreateFindPartnerRequestResponse;
import com.dev.esthomy.dto.response.GetFindPartnerRequestsPageable;
import com.dev.esthomy.models.enums.MemberRole;
import com.dev.esthomy.service.FindPartnerRequestService;
import com.dev.esthomy.util.ObjectConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/find-partner-request")
@RequiredArgsConstructor
public class FindPartnerRequestController {
    private final FindPartnerRequestService findPartnerRequestService;
    private final ObjectConverter objectConverter;

    @PostMapping
    public ResponseEntity<CreateFindPartnerRequestResponse> create(@RequestPart("partnerReq") String request,
                                                                   @RequestHeader("id") final UUID id,
                                                                   @RequestHeader("member-role") final MemberRole role,
                                                                   @RequestPart("files") List<MultipartFile> files) {
        final CreateFindPartnerRequest createFindPartnerRequest = objectConverter.convert(request);
        return ResponseEntity.ok().body(findPartnerRequestService.create(id, role, createFindPartnerRequest, files));
    }

    @GetMapping
    public ResponseEntity<GetFindPartnerRequestsPageable> get(@RequestHeader("id") final UUID id,
                                                              @RequestParam(value = "size", defaultValue = "20") final int pageSize,
                                                              @RequestParam(value = "page", defaultValue = "0") final int pageNumber) {
        return ResponseEntity.ok().body(findPartnerRequestService.get(id, pageSize, pageNumber));
    }

    @GetMapping("/all")
    public ResponseEntity<GetFindPartnerRequestsPageable> getAll(@RequestHeader("member-role") final MemberRole role,
                                                                 @RequestParam(value = "size", defaultValue = "20") final int pageSize,
                                                                 @RequestParam(value = "page", defaultValue = "0") final int pageNumber) {
        return ResponseEntity.ok().body(findPartnerRequestService.getAll(role, pageSize, pageNumber));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindPartnerRequestDto> getFindPartnerRequest(@PathVariable("id") final UUID id) {
        return ResponseEntity.ok().body(findPartnerRequestService.getFindPartnerRequest(id));
    }
}