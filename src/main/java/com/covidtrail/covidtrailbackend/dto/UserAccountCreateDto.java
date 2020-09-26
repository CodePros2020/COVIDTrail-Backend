package com.covidtrail.covidtrailbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAccountCreateDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private AddressCreateDto address;
    private String password;
}
