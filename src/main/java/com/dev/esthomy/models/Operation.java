package com.dev.esthomy.models;

import com.dev.esthomy.models.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    @OneToOne
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;

    @Enumerated
    private Status status;
}
