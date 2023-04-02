package com.dev.esthomy.dto.response;

import com.dev.esthomy.dto.HospitalDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HospitalListResponse {
    public List<HospitalDto> hospitals;
}
