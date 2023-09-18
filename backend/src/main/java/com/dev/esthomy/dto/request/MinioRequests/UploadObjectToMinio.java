package com.dev.esthomy.dto.request.MinioRequests;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@Builder
public class UploadObjectToMinio {
    private String email;
    private String file;
}
