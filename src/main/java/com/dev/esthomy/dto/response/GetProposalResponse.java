package com.dev.esthomy.dto.response;

import com.dev.esthomy.dto.ProposalDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetProposalResponse {
    ProposalDto proposal;
}
