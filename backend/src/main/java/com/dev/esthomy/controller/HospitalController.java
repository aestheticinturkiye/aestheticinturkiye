package com.dev.esthomy.controller;

import com.dev.esthomy.converter.HospitalConverter;
import com.dev.esthomy.dto.request.CreateHospitalRequest;
import com.dev.esthomy.dto.response.CreateHospitalResponse;
import com.dev.esthomy.dto.response.HospitalListResponse;
import com.dev.esthomy.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    private final HospitalConverter hospitalConverter;

    @PostMapping
    public ResponseEntity<CreateHospitalResponse> create(@RequestBody CreateHospitalRequest request){
        return ResponseEntity.ok().body(hospitalConverter.toResponse(hospitalService.create(request)));
    }

    @GetMapping
    public ResponseEntity<HospitalListResponse> listAll() {
        return ResponseEntity.ok().body(hospitalConverter.toResponseList(hospitalService.listAll()));
    }
}
