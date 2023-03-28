package com.esthomy.version1.converter;

import com.esthomy.version1.model.Hospital;
import com.esthomy.version1.model.request.CreateHospitalRequest;
import org.springframework.stereotype.Component;

@Component
public class RequestToHospitalModel {

    public Hospital toModel(CreateHospitalRequest request)
    {
        return Hospital.builder()
                .name(request.getName())
                .location(request.getLocation())
                .build();
    }
}
