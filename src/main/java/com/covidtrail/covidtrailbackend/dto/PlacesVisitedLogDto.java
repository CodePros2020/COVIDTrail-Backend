package com.covidtrail.covidtrailbackend.dto;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class PlacesVisitedLogDto {
    private int id;
    private String visitedDate;
    private String visitedDateTime;
    private UserAccountDto userAccount;
    private BusinessAccountDto businessAccount;
}
