package com.covidtrail.covidtrailbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
