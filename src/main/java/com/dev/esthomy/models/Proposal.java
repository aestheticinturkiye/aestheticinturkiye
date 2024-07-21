package com.dev.esthomy.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@NamedEntityGraph(name = "proposal.all", attributeNodes = {
        @NamedAttributeNode("findPartnerRequest"),
        @NamedAttributeNode("broker")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Proposal {
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "find_partner_request_id")
    private FindPartnerRequest findPartnerRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_id")
    private Broker broker;

    private String clientId;

    private BigDecimal price;

    private Date operationDate;

    private String accommodation;

    private String transportation;

    private String city;

    private String hospital;

    private String description;
}
