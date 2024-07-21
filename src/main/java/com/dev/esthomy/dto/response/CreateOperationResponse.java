package com.dev.esthomy.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateOperationResponse {
    private UUID id;
}
