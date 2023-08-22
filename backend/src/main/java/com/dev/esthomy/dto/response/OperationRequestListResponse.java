package com.dev.esthomy.dto.response;

import com.dev.esthomy.dto.OperationRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OperationRequestListResponse {
    @JsonIgnoreProperties("userAccount")
    public List<OperationRequestDto> operationRequestDtos;
}
