package com.dev.esthomy.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@NamedEntityGraph(name = "client.findPartnerRequest", attributeNodes = {@NamedAttributeNode("findPartnerRequests")})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String name;
    private String surname;
    private int age;
    private String country;
    private String phone;
    private String password;
    private String gender;
    private Boolean isActive;
    private String email;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<FindPartnerRequest> findPartnerRequests;
}