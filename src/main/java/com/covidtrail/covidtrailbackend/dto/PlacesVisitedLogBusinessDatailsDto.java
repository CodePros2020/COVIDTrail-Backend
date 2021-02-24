package com.covidtrail.covidtrailbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlacesVisitedLogBusinessDatailsDto {
    private int id;
    private String visitedDateTime;
    private BusinessAccountDto businessAccount;
}
