package com.covidtrail.covidtrailbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountDetailsDto {
    private int id;
    private String businessName;
    private String firstName;
    private String middleName;
    private String lastName;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String province;
    private String postalCode;
    private String email;
    private String phone;
    private String password;
}
