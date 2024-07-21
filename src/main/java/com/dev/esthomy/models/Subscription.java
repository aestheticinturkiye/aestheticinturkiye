package com.dev.esthomy.models;

import com.dev.esthomy.models.enums.AestheticType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private String email;

    @Column(name = "aesthetic_type")
    @Enumerated
    private AestheticType aestheticType;

}
