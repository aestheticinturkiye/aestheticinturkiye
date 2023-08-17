package com.dev.esthomy.dto.response;

import com.dev.esthomy.dto.AccountDto;
import com.dev.esthomy.dto.OperationRequestDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class AccountListResponse {
    public List<AccountDto> accountDtos;
    public OperationRequestDto operationRequestDto;
}
