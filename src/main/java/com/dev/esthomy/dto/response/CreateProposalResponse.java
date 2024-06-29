package com.dev.esthomy.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProposalResponse {
    private String id;
    private String chatId;
}
