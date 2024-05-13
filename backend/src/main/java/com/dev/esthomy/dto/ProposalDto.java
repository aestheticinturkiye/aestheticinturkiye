package com.dev.esthomy.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class ProposalDto {
    private BigDecimal price;
    private Date operationDate;
    private String accommodation;
    private String city;
    private String transportation;
    private String hospital;
    private String description;
}
