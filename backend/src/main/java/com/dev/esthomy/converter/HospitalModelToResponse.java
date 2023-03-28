package com.dev.esthomy.converter;

import com.dev.esthomy.models.Hospital;
import com.dev.esthomy.models.response.CreateHospitalResponse;
import org.springframework.stereotype.Component;

@Component
public class HospitalModelToResponse {

    public CreateHospitalResponse toResponse(Hospital hospital){
        return CreateHospitalResponse.builder()
                .name(hospital.getName())
                .build();
    }
}
