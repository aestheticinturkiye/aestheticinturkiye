package com.dev.esthomy.service;

import com.dev.esthomy.converter.HospitalModelToDto;
import com.dev.esthomy.converter.HospitalModelToResponse;
import com.dev.esthomy.converter.RequestToHospitalModel;
import com.dev.esthomy.dto.HospitalDto;
import com.dev.esthomy.models.request.CreateHospitalRequest;
import com.dev.esthomy.models.response.CreateHospitalResponse;
import com.dev.esthomy.models.response.HospitalListResponse;
import com.dev.esthomy.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    private final RequestToHospitalModel requestToHospitalModel;
    private final HospitalModelToResponse hospitalModelToResponse;

    private final HospitalModelToDto hospitalModelToDto;

    public CreateHospitalResponse create(CreateHospitalRequest request){
         return hospitalModelToResponse.
                 toResponse(hospitalRepository.save(requestToHospitalModel.toModel(request)));
    }

    public HospitalListResponse listAll() {
        return hospitalModelToResponse.toResponse(hospitalModelToDto.toList(hospitalRepository.findAll()));
    }
}
