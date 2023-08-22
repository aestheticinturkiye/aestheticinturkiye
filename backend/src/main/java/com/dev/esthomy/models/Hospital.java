package com.dev.esthomy.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Hospital {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name ="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String name;
    private String location;
    private Boolean isActive;
    // Bir hospital birden fazla preRequest bulundurabilir.
    @OneToMany(mappedBy = "hospital",fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OperationOffer> operationOffers = new ArrayList<>();

}