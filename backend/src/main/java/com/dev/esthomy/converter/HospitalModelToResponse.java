package com.dev.esthomy.converter;

import com.dev.esthomy.dto.HospitalDto;
import com.dev.esthomy.models.Hospital;
import com.dev.esthomy.models.response.CreateHospitalResponse;
import com.dev.esthomy.models.response.HospitalListResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HospitalModelToResponse {

    public CreateHospitalResponse toResponse(Hospital hospital){
        return CreateHospitalResponse.builder()
                .name(hospital.getName())
                .build();
    }

    public HospitalListResponse toResponse(List<HospitalDto> hospitals)
    {
        return HospitalListResponse.builder()
                .hospitals(hospitals)
                .build();
    }
}
