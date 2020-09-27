package com.covidtrail.covidtrailbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BusinessAccountCreateDto {
	private String businessName;
	private String email;
	private String phone;
	private AddressCreateDto address;	
	private String password;	
}
