package com.dev.esthomy.models.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationRequestResponse {
    private String clientName;
    private int age;
}
