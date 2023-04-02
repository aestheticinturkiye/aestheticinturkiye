package com.dev.esthomy.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOperationRequestResponse {
    private String clientName;
    private int age;
}
