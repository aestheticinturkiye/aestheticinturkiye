package com.dev.esthomy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDto {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private int age;
    private String country;
    private String gender;

}
