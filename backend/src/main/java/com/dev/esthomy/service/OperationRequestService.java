package com.dev.esthomy.service;

import com.dev.esthomy.converter.operationRequestConverter.OperationRequestRequestToModel;
import com.dev.esthomy.converter.operationRequestConverter.OperationRequestToDto;
import com.dev.esthomy.models.request.OperationRequestRequest;
import com.dev.esthomy.models.response.OperationRequestResponse;
import com.dev.esthomy.repository.OperationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationRequestService {

    private final OperationRequestToDto operationRequestToDto;
    private final OperationRequestRequestToModel operationRequestRequestToModel;
    private final OperationRequestRepository operationRequestRepository;

  //  public OperationRequestResponse create(OperationRequestRequest request) {
    //dto dönülüp tekrar response'a dönülecek
        //return operationRequestRepository.save(operationRequestRequestToModel.toModel(request))

  //  }

}