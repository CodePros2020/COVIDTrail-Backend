package com.covidtrail.covidtrailbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PlacesVisitedLogDto {
    private int id;
    private Date visitedDateTime;
    private UserAccountDto userAccount;
    private BusinessAccountDto businessAccount;
}
