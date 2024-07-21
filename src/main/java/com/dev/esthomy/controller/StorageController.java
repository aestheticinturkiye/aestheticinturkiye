package com.dev.esthomy.controller;

import com.dev.esthomy.models.enums.MemberRole;
import com.dev.esthomy.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class StorageController {
    private final StorageService service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFiles(@RequestHeader("member-role") final MemberRole role,
                                              @RequestParam("files") List<MultipartFile> files,
                                              @RequestParam("partnerReqId") UUID partnerReqId) {
        return ResponseEntity.ok().body(service.uploadFiles(role, files, partnerReqId));
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName){
        byte[] data = service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity.ok()
                .contentLength(data.length)
                .header("Content-type","application/esthomy")
                .header("Content-disposition","attachment; filename=\"" +fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName){
        return ResponseEntity.ok().body(service.deleteFile(fileName));
    }
}
