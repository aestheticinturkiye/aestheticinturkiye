package com.dev.esthomy.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateHospitalRequest {
    private String name;
    private String location;

}
