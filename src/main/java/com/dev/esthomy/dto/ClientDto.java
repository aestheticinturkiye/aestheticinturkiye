package com.dev.esthomy.dto;

import com.dev.esthomy.models.Client;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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
    private List<FindPartnerRequestDto> findPartnerRequests;

    public static ClientDto toDto(final Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .surname(client.getSurname())
                .email(client.getEmail())
                .phone(client.getPhone())
                .age(client.getAge())
                .country(client.getCountry())
                .build();
    }

    public static ClientDto toDtoWithFindPartnerRequest(final Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .surname(client.getSurname())
                .email(client.getEmail())
                .phone(client.getPhone())
                .age(client.getAge())
                .country(client.getCountry())
                .findPartnerRequests(client.getFindPartnerRequests().stream()
                        .map(FindPartnerRequestDto::toDto)
                        .toList())
                .build();
    }

}
