package com.dev.esthomy.dto;

import com.dev.esthomy.models.FindPartnerRequest;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class FindPartnerRequestDto {
    private UUID id;
    private String aestheticType;
    private Date preferedDate;
    private String preferredCity;
    private boolean isNeededAccommodation;
    private boolean isNeededTransportation;
    private String description;
    private List<ProposalDto> proposalDtoList;
    private List<String> imageUrls;
    private ClientDto client;

    public static FindPartnerRequestDto toDtoWithClient(final FindPartnerRequest findPartnerRequest) {
        return FindPartnerRequestDto.builder()
                .id(findPartnerRequest.getId())
                .aestheticType(findPartnerRequest.getAestheticType())
                .preferedDate(findPartnerRequest.getPreferedDate())
                .preferredCity(findPartnerRequest.getPreferredCity())
                .isNeededAccommodation(findPartnerRequest.isNeededAccommodation())
                .isNeededTransportation(findPartnerRequest.isNeededTransportation())
                .description(findPartnerRequest.getDescription())
                .proposalDtoList(findPartnerRequest.getProposals().stream()
                        .map(ProposalDto::toDto)
                        .toList())
                .client(ClientDto.toDto(findPartnerRequest.getClient()))
                .build();
    }

    public static FindPartnerRequestDto toDto(final FindPartnerRequest findPartnerRequest) {
        return FindPartnerRequestDto.builder()
                .id(findPartnerRequest.getId())
                .aestheticType(findPartnerRequest.getAestheticType())
                .preferedDate(findPartnerRequest.getPreferedDate())
                .preferredCity(findPartnerRequest.getPreferredCity())
                .isNeededAccommodation(findPartnerRequest.isNeededAccommodation())
                .isNeededTransportation(findPartnerRequest.isNeededTransportation())
                .description(findPartnerRequest.getDescription())
                .proposalDtoList(findPartnerRequest.getProposals().stream()
                        .map(ProposalDto::toDto)
                        .toList())
                .build();
    }

    public static FindPartnerRequestDto toDtoWithoutProposal(final FindPartnerRequest findPartnerRequest) {
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
