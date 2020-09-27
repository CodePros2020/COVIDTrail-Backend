package com.covidtrail.covidtrailbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PlacesVisitedLogBusinessDatailsDto {
    private int id;
    private Date visitedDateTime;
    private BusinessAccountDto businessAccount;
}
