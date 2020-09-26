package com.covidtrail.covidtrailbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserAccountDto {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDto address;
}
