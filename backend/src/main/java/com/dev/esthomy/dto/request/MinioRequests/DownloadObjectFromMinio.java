package com.dev.esthomy.dto.request.MinioRequests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DownloadObjectFromMinio {
    private String email;
    private String fileName;
}
