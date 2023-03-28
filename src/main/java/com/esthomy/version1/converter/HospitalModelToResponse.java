package com.esthomy.version1.converter;

import com.esthomy.version1.model.Hospital;
import com.esthomy.version1.model.response.CreateHospitalResponse;
import org.springframework.stereotype.Component;

@Component
public class HospitalModelToResponse {

    public CreateHospitalResponse toResponse(Hospital fromModel)
    {
        return CreateHospitalResponse.builder()
                .name(fromModel.getName())
                .build();
    }
}
