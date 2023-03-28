package com.esthomy.version1.controller;

import com.esthomy.version1.model.Hospital;
import com.esthomy.version1.model.request.CreateHospitalRequest;
import com.esthomy.version1.model.response.CreateHospitalResponse;
import com.esthomy.version1.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Controller sadece gelen request türüne bakar ne dönülücekse dönülür error handling yapar ve kayıt yapılacaksa service üzerinden

//GET - POST  GET -> VERİ ÇEKMEK İÇİN / POST VERİ KAYDETMEK
// Post bi body
// Get body // önemli değil

// Put- Patch -Delete
// Put ve Patch update Put -> obje bunun güncelliyo tüm objeyi tekrar kayıt ediyo
// Patch -> Gidiyo objeyi çekiyo sadece verdiğin alanı güncelleyip kayıt ediyo
// Delete -> silme işlemi

@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    //Request CreateHospitalRequest request
    @PostMapping()
    public ResponseEntity<CreateHospitalResponse> create(@RequestBody CreateHospitalRequest request)
    {
        return ResponseEntity.ok().body(hospitalService.create(request));
    }

}
