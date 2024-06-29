package com.dev.esthomy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatDto {
    @JsonProperty("_id")
    private String id;

    @JsonProperty("id")
    private String id2;

    private String chatName;

    @JsonProperty("isGroupChat")
    private boolean groupChat;

    private String proposalId;
    private List<UserDto> users;

    @JsonProperty("__v")
    private int version;

    @JsonProperty("latestMessage")
    private LatestMessageDto latestMessage;

}


