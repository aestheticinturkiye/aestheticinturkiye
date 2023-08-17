package com.dev.esthomy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationOfferDto {
    private String accountId;
    private String operationRequestId;
    private String hospitalId;
    private Double price;
    private String description;
}
