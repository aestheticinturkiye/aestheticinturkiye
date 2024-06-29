package com.dev.esthomy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    @JsonProperty("_id")
    private String id;

    private String name;

    @JsonProperty("isBroker")
    private boolean isBroker;

    @JsonProperty("__v")
    private int version;

    @JsonProperty("createdAt")
    private Date createdAt;

    @JsonProperty("updatedAt")
    private Date updatedAt;
}
