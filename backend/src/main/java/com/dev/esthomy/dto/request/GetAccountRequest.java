package com.dev.esthomy.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetAccountRequest {
    @NotBlank
    private String id;
}
