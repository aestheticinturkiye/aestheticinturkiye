package com.dev.esthomy.dto;

import com.dev.esthomy.models.Account;
import com.dev.esthomy.models.AestheticType;
import com.dev.esthomy.models.OperationOffer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OperationRequestDto {
    //accountDtoya cevir
    @JsonIgnoreProperties("userAccount")
    private Account  userAccount;
    private AestheticType aestheticType;
    private List<OperationOffer> operationOfferList;
}
