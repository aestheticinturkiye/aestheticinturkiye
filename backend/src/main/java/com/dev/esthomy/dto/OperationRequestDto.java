package com.dev.esthomy.dto;

import com.dev.esthomy.models.Account;
import com.dev.esthomy.models.AestheticType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationRequestDto {
    //accountDtoya cevir
    private Account  userAccount;
    private AestheticType aestheticType;
}
