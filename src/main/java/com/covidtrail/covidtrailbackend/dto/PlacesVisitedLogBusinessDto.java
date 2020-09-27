package com.covidtrail.covidtrailbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PlacesVisitedLogBusinessDto {
    private Date visitedDate;
    private List<PlacesVisitedLogBusinessDatailsDto> businessDetails;
}
