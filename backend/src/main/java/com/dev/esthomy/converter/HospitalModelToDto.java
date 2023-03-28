package com.dev.esthomy.converter;

import com.dev.esthomy.dto.HospitalDto;
import com.dev.esthomy.models.Hospital;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HospitalModelToDto {

    public List<HospitalDto> toList(List<Hospital> fromModel)
    {
       return fromModel.stream().map(this::toModel).collect(Collectors.toList());
    }

    public HospitalDto toModel(Hospital fromModel)
    {
        return HospitalDto.builder()
                .name(fromModel.getName())
                .location(fromModel.getLocation())
                .build();
    }
}
