package com.dev.esthomy.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateOperationRequest {
    private UUID proposalId;
}
