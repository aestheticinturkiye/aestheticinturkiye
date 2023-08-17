package com.dev.esthomy.dto.request.operationOfferRequest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOperationOffer {
    private String accountId;
    private String operationRequestId;
    private String hospitalId;
    private Double price;
    private String description;
}
