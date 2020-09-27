package com.covidtrail.covidtrailbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PlacesVisitedLogUserDto {
    private Date visitedDate;
    private List<PlacesVisitedLogUserDetailsDto> userDetails;
}
