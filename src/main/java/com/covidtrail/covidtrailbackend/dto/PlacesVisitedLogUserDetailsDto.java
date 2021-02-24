package com.covidtrail.covidtrailbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlacesVisitedLogUserDetailsDto {
    private int id;
    private String visitedDateTime;
    private int userId;
    private String firstName;
    private String middleName;
    private String lastName;
}
