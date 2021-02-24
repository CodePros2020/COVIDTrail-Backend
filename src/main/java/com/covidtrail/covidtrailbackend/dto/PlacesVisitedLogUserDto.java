package com.covidtrail.covidtrailbackend.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlacesVisitedLogUserDto {
    private String visitedDate;
    private List<PlacesVisitedLogUserDetailsDto> userDetails;
}
