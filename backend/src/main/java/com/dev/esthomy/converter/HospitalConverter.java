package com.dev.esthomy.converter;

import com.dev.esthomy.dto.HospitalDto;
import com.dev.esthomy.models.Hospital;
import com.dev.esthomy.models.request.CreateHospitalRequest;
import com.dev.esthomy.models.response.CreateHospitalResponse;
import com.dev.esthomy.models.response.HospitalListResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HospitalConverter {
    public List<HospitalDto> toList(List<Hospital> fromModel)
    {
        return fromModel.stream().map(this::toDto).collect(Collectors.toList());
    }

    public HospitalDto toDto(Hospital fromModel)
    {
        return HospitalDto.builder()
                .name(fromModel.getName())
                .location(fromModel.getLocation())
                .build();
    }

    public CreateHospitalResponse toResponse(HospitalDto hospitalDto)
    {
        return CreateHospitalResponse.builder()
                .name(hospitalDto.getName())
                .location(hospitalDto.getLocation())
                .build();
    }

    public HospitalListResponse toResponseList(List<HospitalDto> hospitals)
    {
        return HospitalListResponse.builder()
                .hospitals(hospitals)
                .build();
    }

    public Hospital toModel(CreateHospitalRequest request){
        return Hospital.builder()
                .name(request.getName())
                .location(request.getLocation())
                .build();
    }

}
