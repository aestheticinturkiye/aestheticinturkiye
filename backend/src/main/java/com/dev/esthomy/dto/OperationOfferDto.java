package com.dev.esthomy.dto;

import com.dev.esthomy.models.Account;
import com.dev.esthomy.models.Hospital;
import com.dev.esthomy.models.OperationRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationOfferDto {
    private String accountId;
    private String operationRequestId;
    private String hospitalId;
    private Boolean isApproved;
}
