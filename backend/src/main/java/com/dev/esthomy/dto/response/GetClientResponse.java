package com.dev.esthomy.dto.response;

import com.dev.esthomy.dto.ClientDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetClientResponse {
    private ClientDto client;
}
