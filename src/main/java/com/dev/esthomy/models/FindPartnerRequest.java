package com.dev.esthomy.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedEntityGraph(name = "FindPartnerRequest.proposals",
attributeNodes = @NamedAttributeNode("proposals"))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindPartnerRequest {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name ="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String clientId;
    private String aestheticType;
    private Date preferedDate;
    private String preferredCity;

    @OneToMany(mappedBy = "findPartnerRequest" , cascade = CascadeType.ALL)
    private List<Proposal> proposals = new ArrayList<>();
    private boolean isNeededAccommodation;
    private boolean isNeededTransportation;
    private String description;

}
