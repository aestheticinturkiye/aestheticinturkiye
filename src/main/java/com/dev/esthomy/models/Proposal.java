package com.dev.esthomy.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Proposal {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne()
    @JoinColumn(name = "findPartnerRequest_id")
    private FindPartnerRequest findPartnerRequest;
    private String clientId;
    private String brokerId;
    private BigDecimal price;
    private Date operationDate;
    private String accommodation;
    private String transportation;
    private String city;
    private String hospital;
    private String description;
}
