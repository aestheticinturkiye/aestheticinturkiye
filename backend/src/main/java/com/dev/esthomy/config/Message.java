package com.dev.esthomy.config;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {

    private String senderName;
    private String recevierName;

    private  String message;
    private LocalDateTime dateTime= LocalDateTime.now();

    private Status status;
}
