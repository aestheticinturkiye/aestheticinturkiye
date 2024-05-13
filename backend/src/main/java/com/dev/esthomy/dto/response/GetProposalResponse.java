package com.dev.esthomy.dto.response;

import com.dev.esthomy.dto.ProposalDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetProposalResponse {
    private List<ProposalDto> proposals;
}
