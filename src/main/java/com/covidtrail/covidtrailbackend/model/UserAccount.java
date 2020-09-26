package com.covidtrail.covidtrailbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserAccount {
    private int id;
    private Date createdDate;
    private Date lastModifiedDateTime;
    private Date deletedDateTime;
    private int deleted;
    private String firstName;
    private String middleName;
    private String lastName;
    private int addressId;
    private String email;
    private String phone;
    private String password;
}
