package com.dev.esthomy.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateClientResponse {
    private String id;
    private String name;
    private String email;
}
