package com.dev.esthomy.service;

import com.dev.esthomy.converter.HospitalModelToResponse;
import com.dev.esthomy.converter.RequestToHospitalModel;
import com.dev.esthomy.models.request.CreateHospitalRequest;
import com.dev.esthomy.models.response.CreateHospitalResponse;
import com.dev.esthomy.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    private final RequestToHospitalModel requestToHospitalModel;
    private final HospitalModelToResponse hospitalModelToResponse;

    public CreateHospitalResponse create(CreateHospitalRequest request){
         return hospitalModelToResponse.
                 toResponse(hospitalRepository.save(requestToHospitalModel.toModel(request)));
    }
}
