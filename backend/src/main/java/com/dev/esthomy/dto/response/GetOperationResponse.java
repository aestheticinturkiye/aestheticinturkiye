package com.dev.esthomy.dto.response;


import com.dev.esthomy.dto.OperationDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetOperationResponse {

    private OperationDto operationDto;
}
