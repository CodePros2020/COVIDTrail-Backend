package com.covidtrail.covidtrailbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Address {
    private int id;
    private Date createdDate;
    private Date lastModifiedDateTime;
    private Date deletedDateTime;
    private int deleted;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String province;
    private String postalCode;
}
