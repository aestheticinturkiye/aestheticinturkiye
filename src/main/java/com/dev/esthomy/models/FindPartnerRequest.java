package com.dev.esthomy.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;

@Entity
@NamedEntityGraph(name = "findPartnerRequest.all", attributeNodes = {@NamedAttributeNode("client"), @NamedAttributeNode("proposals")})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindPartnerRequest {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    private String aestheticType;
    private Date preferedDate;
    private String preferredCity;

    @OneToMany(mappedBy = "findPartnerRequest", fetch = FetchType.LAZY)
    private List<Proposal> proposals;

    private boolean isNeededAccommodation;
    private boolean isNeededTransportation;
    private String description;

}
