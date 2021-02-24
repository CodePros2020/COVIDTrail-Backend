package com.covidtrail.covidtrailbackend.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlacesVisitedLogBusinessDto {
    private String visitedDate;
    private List<PlacesVisitedLogBusinessDatailsDto> businessDetails;
}
