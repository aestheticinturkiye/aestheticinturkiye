package com.dev.esthomy.dto;

import com.dev.esthomy.models.Broker;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrokerDto {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private int age;
    private String country;
    private String gender;

    public static BrokerDto toDto(final Broker broker) {
        return BrokerDto.builder()
                .id(broker.getId())
                .name(broker.getName())
                .surname(broker.getSurname())
                .email(broker.getEmail())
                .phone(broker.getPhone())
                .age(broker.getAge())
                .country(broker.getCountry())
                .build();
    }
}
