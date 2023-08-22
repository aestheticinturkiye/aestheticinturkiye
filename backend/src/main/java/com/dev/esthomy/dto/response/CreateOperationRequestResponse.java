package com.dev.esthomy.dto.response;

import com.dev.esthomy.models.AestheticType;
import com.dev.esthomy.models.OperationOffer;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateOperationRequestResponse {
    private AestheticType aestheticType;
    private String userId;
    private List<OperationOffer> operationOfferList;

}
