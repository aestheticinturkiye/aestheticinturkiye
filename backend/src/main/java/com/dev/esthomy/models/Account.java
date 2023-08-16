package com.dev.esthomy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnoreProperties("userAccount")
    @OneToMany(mappedBy = "userAccount",fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<OperationRequest> operationRequests = new ArrayList<>();

}