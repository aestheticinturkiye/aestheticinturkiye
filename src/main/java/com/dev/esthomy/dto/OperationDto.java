package com.dev.esthomy.dto;


import com.dev.esthomy.models.enums.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationDto {
    private String proposalId;
    private Status status;
}
