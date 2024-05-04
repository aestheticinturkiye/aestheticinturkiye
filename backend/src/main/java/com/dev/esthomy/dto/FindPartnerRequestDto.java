package com.dev.esthomy.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FindPartnerRequestDto {
    private String aestheticType;
    private Date preferedDate;
    private String preferredCity;
    private boolean isNeededAccommodation;
    private boolean isNeededTransportation;
    private String description;
}
