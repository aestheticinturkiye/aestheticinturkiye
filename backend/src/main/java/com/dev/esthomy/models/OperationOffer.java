package com.dev.esthomy.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationOffer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name ="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id",nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "hospital_id",nullable = false)
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "operation_id",nullable = false)
    private OperationRequest operationRequest;

    private Boolean isApproved = false;

    //price-description-broker-buraya mi ?
}
