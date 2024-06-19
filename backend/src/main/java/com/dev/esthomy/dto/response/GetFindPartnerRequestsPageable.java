package com.dev.esthomy.dto.response;

import com.dev.esthomy.dto.FindPartnerRequestDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetFindPartnerRequestsPageable {
    private List<FindPartnerRequestDto> list;
    private int totalPages;
    private long totalElements;
}
