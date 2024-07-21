package com.dev.esthomy.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateBrokerResponse {
    private UUID id;
    private String name;
    private String email;
}
