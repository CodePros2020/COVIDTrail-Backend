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

	public CustomUser build() {
		return new CustomUser(this);
	}

}
