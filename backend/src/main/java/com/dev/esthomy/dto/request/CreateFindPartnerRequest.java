package com.dev.esthomy.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class CreateFindPartnerRequest {
    private String aestheticType;
    private Date preferedDate;
    private String preferredCity;
    private boolean isNeededAccommodation;
    private boolean isNeededTransportation;
    private String description;
}
