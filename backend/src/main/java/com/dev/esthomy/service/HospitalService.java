package com.dev.esthomy.service;

import com.dev.esthomy.converter.HospitalConverter;
import com.dev.esthomy.dto.HospitalDto;
import com.dev.esthomy.models.request.CreateHospitalRequest;
import com.dev.esthomy.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    private final HospitalConverter hospitalConverter;

    public HospitalDto create(CreateHospitalRequest request){
         return (hospitalConverter.toDto(hospitalRepository.save(hospitalConverter.toModel(request))));
    }

    public List<HospitalDto> listAll() {
        return hospitalConverter.toList(hospitalRepository.findAll());
    }
}
