package com.dev.esthomy.jwt.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token {
    private String value;
    private Long duration;
}
