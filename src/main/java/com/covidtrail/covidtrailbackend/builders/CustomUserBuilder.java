package com.covidtrail.covidtrailbackend.builders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

import com.covidtrail.covidtrailbackend.model.CustomUser;

import lombok.Getter;

@Getter
public class CustomUserBuilder {

	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int deleted;
	private Collection<GrantedAuthority> authorities;

	public CustomUserBuilder id(int id) {
		this.id = id;
		return this;
	}

	public CustomUserBuilder username(String username) {
		this.username = username;
		return this;
	}

	public CustomUserBuilder password(String password) {
		this.password = password;
		return this;
	}

	public CustomUserBuilder roles(String... roles) {

		List<GrantedAuthority> authorities = new ArrayList<>(
				roles.length);

		if (roles != null && roles.length > 0) {
			for (String role : roles) {
				Assert.isTrue(!role.startsWith("ROLE_"), () -> role
						+ " cannot start with ROLE_ (it is automatically added)");
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
			}
		}
		this.authorities = authorities;
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

	public CustomUserBuilder deleted(int deleted) {
		this.deleted = deleted;
		return this;
	}

	public CustomUser build() {
		return new CustomUser(this);
	}

}
