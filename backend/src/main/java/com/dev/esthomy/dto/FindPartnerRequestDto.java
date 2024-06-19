package com.dev.esthomy.dto;

import com.dev.esthomy.models.FindPartnerRequest;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class FindPartnerRequestDto {
    private String id;
    private String aestheticType;
    private Date preferedDate;
    private String preferredCity;
    private boolean isNeededAccommodation;
    private boolean isNeededTransportation;
    private String description;
    private List<ProposalDto> proposalDtoList;
    private List<String> imageUrls;
    private ClientDto client;

    public static FindPartnerRequestDto toDto(FindPartnerRequest findPartnerRequest) {
        return FindPartnerRequestDto.builder()
                .id(findPartnerRequest.getId())
                .aestheticType(findPartnerRequest.getAestheticType())
                .preferedDate(findPartnerRequest.getPreferedDate())
                .preferredCity(findPartnerRequest.getPreferredCity())
                .isNeededAccommodation(findPartnerRequest.isNeededAccommodation())
                .isNeededTransportation(findPartnerRequest.isNeededTransportation())
                .description(findPartnerRequest.getDescription())
                .build();
    }
}
