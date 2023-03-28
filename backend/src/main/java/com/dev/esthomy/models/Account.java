package com.dev.esthomy.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name ="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String name;
    private String surname;
    private LocalDateTime createdDate = LocalDateTime.now();
    private int age;
    private String country;
    private String phone;
    private String password;
    private String gender;
    private Boolean isActive;
    private String email;

    // Bir user birden fazla preRequest bulundurabilir.
    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<PreRequest> preRequests = new ArrayList<>();

}