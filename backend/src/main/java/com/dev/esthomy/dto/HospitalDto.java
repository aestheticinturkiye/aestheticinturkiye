package com.dev.esthomy.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HospitalDto {
    private String name;
    private String location;
}
