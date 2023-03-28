package com.dev.esthomy.controller;

import com.dev.esthomy.models.request.CreateHospitalRequest;
import com.dev.esthomy.models.response.CreateHospitalResponse;
import com.dev.esthomy.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    @PostMapping()
    public ResponseEntity<CreateHospitalResponse> create(@RequestBody CreateHospitalRequest request){
        return ResponseEntity.ok().body(hospitalService.create(request));
    }
}
