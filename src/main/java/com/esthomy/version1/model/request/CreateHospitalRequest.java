package com.esthomy.version1.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateHospitalRequest {
    private String name;
    private String location;

}
