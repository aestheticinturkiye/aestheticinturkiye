package com.dev.esthomy.dto;


import com.dev.esthomy.models.Proposal;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class ProposalDto {
    private UUID id;
    private BigDecimal price;
    private Date operationDate;
    private String accommodation;
    private String city;
    private String transportation;
    private String hospital;
    private String description;
    private FindPartnerRequestDto findPartnerRequest;
    private BrokerDto broker;

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
                .findPartnerRequest(FindPartnerRequestDto.toDtoWithoutProposal(proposal.getFindPartnerRequest()))
                .broker(BrokerDto.toDto(proposal.getBroker()))
                .build();
    }
}
