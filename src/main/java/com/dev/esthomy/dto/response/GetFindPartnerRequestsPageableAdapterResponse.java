package com.dev.esthomy.dto.response;

import com.dev.esthomy.models.FindPartnerRequest;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetFindPartnerRequestsPageableAdapterResponse {
    private List<FindPartnerRequest> list;
    private int totalPages;
    private long totalElements;
}
