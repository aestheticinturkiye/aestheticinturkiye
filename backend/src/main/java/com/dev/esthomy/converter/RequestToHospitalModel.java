package com.dev.esthomy.converter;

import com.dev.esthomy.models.Hospital;
import com.dev.esthomy.models.request.CreateHospitalRequest;
import org.springframework.stereotype.Component;

@Component
public class RequestToHospitalModel {

    public Hospital toModel(CreateHospitalRequest request){
        return Hospital.builder()
                .name(request.getName())
                .location(request.getLocation())
                .build();
    }

}
