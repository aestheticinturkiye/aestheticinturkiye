package com.dev.esthomy.dto.response;

import com.dev.esthomy.models.Account;
import com.dev.esthomy.models.AestheticType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOperationRequestResponse {
    private Account userAccount;
    private AestheticType aestheticType;
}
