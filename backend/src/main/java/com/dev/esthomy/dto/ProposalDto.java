package com.dev.esthomy.dto;


import com.dev.esthomy.models.Proposal;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class ProposalDto {
    private String id;
    private BigDecimal price;
    private Date operationDate;
    private String accommodation;
    private String city;
    private String transportation;
    private String hospital;
    private String description;

    public static ProposalDto toDto(final Proposal proposal) {
        return ProposalDto.builder()
                .id(proposal.getId())
                .price(proposal.getPrice())
                .operationDate(proposal.getOperationDate())
                .accommodation(proposal.getAccommodation())
                .city(proposal.getCity())
                .transportation(proposal.getTransportation())
                .hospital(proposal.getHospital())
                .description(proposal.getDescription())
                .build();
    }
}
