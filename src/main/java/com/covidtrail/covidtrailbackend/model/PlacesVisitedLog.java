package com.covidtrail.covidtrailbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PlacesVisitedLog {
    private int id;
    private Date createdDate;
    private Date lastModifiedDateTime;
    private Date deletedDateTime;
    private int deleted;
    private int userAccountId;
    private int businessAccountId;
    private Date visitedDateTime;
}
