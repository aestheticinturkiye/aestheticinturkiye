package com.dev.esthomy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class LatestMessageDto {
    @JsonProperty("_id")
    private String id;

    @JsonProperty("sender")
    private UserDto sender;

    @JsonProperty("content")
    private String content;

    @JsonProperty("chat")
    private String chatId;

    @JsonProperty("__v")
    private int version;
}
