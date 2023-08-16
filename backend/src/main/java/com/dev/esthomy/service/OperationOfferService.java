package com.dev.esthomy.service;

import com.dev.esthomy.repository.OperationOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationOfferService {

    private final OperationOfferRepository operationOfferRepository;

}
