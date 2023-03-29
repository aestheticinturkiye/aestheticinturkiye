package com.dev.esthomy.controller;

import com.dev.esthomy.models.request.CreateHospitalRequest;
import com.dev.esthomy.models.response.CreateHospitalResponse;
import com.dev.esthomy.models.response.HospitalListResponse;
import com.dev.esthomy.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    @PostMapping
    public ResponseEntity<CreateHospitalResponse> create(@RequestBody CreateHospitalRequest request){
        return ResponseEntity.ok().body(hospitalService.create(request));
    }

    @GetMapping
    public ResponseEntity<HospitalListResponse> listAll()
    {
        return ResponseEntity.ok().body(hospitalService.listAll());
    }

}
