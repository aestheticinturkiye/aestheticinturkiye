package com.dev.esthomy.models;

import com.dev.esthomy.validator.aspect.ValidateEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationRequest {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name ="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id",nullable = false)
    @JsonIgnoreProperties("operationRequests")
    private Account userAccount;

    @OneToMany(mappedBy = "operationRequest",fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<OperationOffer> operationOfferList = new ArrayList<>();

    private AestheticType aestheticType;
}
