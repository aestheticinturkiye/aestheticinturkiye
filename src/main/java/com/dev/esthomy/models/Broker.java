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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Broker {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String name;
    private String surname;
    private int age;
    private String password;
    private String gender;
    private String address;
    private String email;
    private String country;
    private Boolean isActive;
    private String phone;

    @OneToMany(mappedBy = "broker", fetch = FetchType.LAZY)
    private List<Proposal> proposals;
}
