package com.dev.esthomy.dto.request.operationRequest;

import com.dev.esthomy.models.Account;
import com.dev.esthomy.models.AestheticType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOperationRequest {
    private String email;
    private AestheticType aestheticType;

}
