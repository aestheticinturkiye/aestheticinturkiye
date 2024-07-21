package com.dev.esthomy.models;

import com.dev.esthomy.models.enums.CredentialStatus;
import com.dev.esthomy.models.enums.MemberRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Credential {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String email;

    private String password;

    @Enumerated
    private MemberRole memberRole;

    @Enumerated(EnumType.STRING)
    private CredentialStatus status;
}
