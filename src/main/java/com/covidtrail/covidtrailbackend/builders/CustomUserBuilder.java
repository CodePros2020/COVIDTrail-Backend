package com.covidtrail.covidtrailbackend.builders;

import com.covidtrail.covidtrailbackend.model.CustomUser;

import lombok.Getter;

@Getter
public class CustomUserBuilder {

	private int id;
	private String phone;
	private String password;
	private String firstName;
	private String lastName;
	private String businessName;
	private String email;
	private String addressLineOne;
	private String addressLineTwo;
	private String province;
	private String postalCode;
	private String city;
	private String middleName;

	public CustomUserBuilder id(int id) {
		this.id = id;
		return this;
	}

	public CustomUserBuilder phone(String phone) {
		this.phone = phone;
		return this;
	}

	public CustomUserBuilder password(String password) {
		this.password = password;
		return this;
	}

	public CustomUserBuilder firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public CustomUserBuilder lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public CustomUserBuilder businessName(String businessName) {
		this.businessName = businessName;
		return this;
	}

	public CustomUserBuilder email(String email) {
		this.email = email;
		return this;
	}
	
	public CustomUserBuilder addressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
		return this;
	}
	
	public CustomUserBuilder addressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
		return this;
	}
	
	public CustomUserBuilder province(String province) {
		this.province = province;
		return this;
	}
	
	public CustomUserBuilder postalCode(String postalCode) {
		this.postalCode = postalCode;
		return this;
	}
	
	public CustomUserBuilder city(String city) {
		this.city = city;
		return this;
	}
	
	public CustomUserBuilder middleName(String middleName) {
		this.middleName = middleName;
		return this;
	}
	
	public CustomUser build() {
		return new CustomUser(this);
	}

}
