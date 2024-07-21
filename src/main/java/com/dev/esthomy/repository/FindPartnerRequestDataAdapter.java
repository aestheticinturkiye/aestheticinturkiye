package com.dev.esthomy.repository;

import com.dev.esthomy.dto.response.GetFindPartnerRequestsPageableAdapterResponse;
import com.dev.esthomy.models.FindPartnerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class FindPartnerRequestDataAdapter {
    private final FindPartnerRequestSpecification findPartnerRequestSpecification;
    private final FindPartnerRequestRepository findPartnerRequestRepository;

    public GetFindPartnerRequestsPageableAdapterResponse getFindPartnerRequestPageable(final int pageSize,
                                                                                       final int pageNumber) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());

        final Page<FindPartnerRequest> findPartnerRequestPage = findPartnerRequestRepository.findAll(pageable);

        return GetFindPartnerRequestsPageableAdapterResponse.builder()
                .list(findPartnerRequestPage.getContent())
                .totalPages(findPartnerRequestPage.getTotalPages())
                .totalElements(findPartnerRequestPage.getTotalElements())
                .build();
    }

    public GetFindPartnerRequestsPageableAdapterResponse getClientFindPartnerRequestPageable(final UUID clientId,
                                                                                             final int pageSize,
                                                                                             final int pageNumber) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());

        final Page<FindPartnerRequest> findPartnerRequestPage = findPartnerRequestRepository
                .findAll(findPartnerRequestSpecification.getClientFindPartnerRequestSpecification(clientId), pageable);

        return GetFindPartnerRequestsPageableAdapterResponse.builder()
                .list(findPartnerRequestPage.getContent())
                .totalPages(findPartnerRequestPage.getTotalPages())
                .totalElements(findPartnerRequestPage.getTotalElements())
                .build();
    }
}
