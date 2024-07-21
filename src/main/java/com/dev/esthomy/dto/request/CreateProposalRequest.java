package com.dev.esthomy.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
public class CreateProposalRequest {
    private BigDecimal price;
    private UUID findPartnerRequestId;
    private Date operationDate;
    private String accommodation;
    private String city;
    private String transportation;
    private String hospital;
    private String description;
}
