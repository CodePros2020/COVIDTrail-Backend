package com.covidtrail.covidtrailbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {
    private int id;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String province;
    private String postalCode;
}
