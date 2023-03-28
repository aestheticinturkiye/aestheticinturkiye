package com.dev.esthomy.models;

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
    private List<PreRequest> preRequests = new ArrayList<>();

}