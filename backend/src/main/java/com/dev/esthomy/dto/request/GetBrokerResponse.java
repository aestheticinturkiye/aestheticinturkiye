package com.dev.esthomy.dto.request;

import com.dev.esthomy.dto.BrokerDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetBrokerResponse {
    private BrokerDto broker;
}
