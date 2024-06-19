package com.dev.esthomy.repository;

import com.dev.esthomy.dto.FindPartnerRequestDto;
import com.dev.esthomy.dto.response.GetFindPartnerRequestsPageable;
import com.dev.esthomy.models.FindPartnerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FindPartnerRequestDataAdapter {
    private final FindPartnerRequestSpecification findPartnerRequestSpecification;
    private final FindPartnerRequestRepository findPartnerRequestRepository;

    public GetFindPartnerRequestsPageable getFindPartnerRequestPageable(final int pageSize,
                                                                        final int pageNumber) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());

        final Page<FindPartnerRequest> findPartnerRequestPage = findPartnerRequestRepository.findAll(pageable);

        final List<FindPartnerRequestDto> findPartnerRequestList = findPartnerRequestPage.getContent()
                .stream().map(FindPartnerRequestDto::toDto).toList();


        return GetFindPartnerRequestsPageable.builder()
                .list(findPartnerRequestList)
                .totalPages(findPartnerRequestPage.getTotalPages())
                .totalElements(findPartnerRequestPage.getTotalElements())
                .build();
    }

    public GetFindPartnerRequestsPageable getClientFindPartnerRequestPageable(final String clientId,
                                                                              final int pageSize,
                                                                              final int pageNumber) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());

        final Page<FindPartnerRequest> findPartnerRequestPage = findPartnerRequestRepository
                .findAll(findPartnerRequestSpecification.getClientFindPartnerRequestSpecification(clientId), pageable);

        final List<FindPartnerRequestDto> findPartnerRequestList = findPartnerRequestPage.getContent()
                .stream().map(FindPartnerRequestDto::toDto).toList();

        return GetFindPartnerRequestsPageable.builder()
                .list(findPartnerRequestList)
                .totalPages(findPartnerRequestPage.getTotalPages())
                .totalElements(findPartnerRequestPage.getTotalElements())
                .build();
    }
}
