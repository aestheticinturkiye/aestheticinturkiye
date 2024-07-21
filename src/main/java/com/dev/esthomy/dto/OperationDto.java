package com.dev.esthomy.dto;


import com.dev.esthomy.models.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OperationDto {
    private UUID proposalId;
    private Status status;
}
