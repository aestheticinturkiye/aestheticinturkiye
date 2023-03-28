package com.esthomy.version1.service;

import com.esthomy.version1.converter.HospitalModelToResponse;
import com.esthomy.version1.converter.RequestToHospitalModel;
import com.esthomy.version1.model.Hospital;
import com.esthomy.version1.model.request.CreateHospitalRequest;
import com.esthomy.version1.model.response.CreateHospitalResponse;
import com.esthomy.version1.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    private final RequestToHospitalModel requestToHospitalModel;

    private final HospitalModelToResponse hospitalModelToResponse;
    public CreateHospitalResponse create(CreateHospitalRequest request) {
        return hospitalModelToResponse
                .toResponse(hospitalRepository.save
                        (requestToHospitalModel.toModel(request)));
    };

}
