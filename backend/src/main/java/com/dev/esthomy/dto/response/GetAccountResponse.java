package com.dev.esthomy.dto.response;

import com.dev.esthomy.dto.AccountDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAccountResponse {
    private AccountDto accountInfo;
}
