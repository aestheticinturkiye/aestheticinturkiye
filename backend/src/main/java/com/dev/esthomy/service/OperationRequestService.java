package com.dev.esthomy.service;

import com.dev.esthomy.converter.OperationConverter;
import com.dev.esthomy.dto.OperationRequestDto;
import com.dev.esthomy.models.request.OperationRequestRequest;
import com.dev.esthomy.repository.OperationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationRequestService {
    private final OperationConverter operationConverter;

    private final OperationRequestRepository operationRequestRepository;

    public OperationRequestDto create(OperationRequestRequest request) {
        return operationConverter.toDto(
                operationRequestRepository.save(
                        operationConverter.toModel(request)));
    }

}