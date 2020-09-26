package com.covidtrail.covidtrailbackend.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.covidtrail.covidtrailbackend.builders.CustomUserBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CustomUser extends User {

	private static final long serialVersionUID = 5815867210632690356L;
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int deleted;
	private Collection<GrantedAuthority> authorities;
	
	public CustomUser(int id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public CustomUser(CustomUserBuilder builder) {
		super(builder.getUsername(), builder.getPassword(), builder.getAuthorities());
		this.id = builder.getId();
		this.username = builder.getUsername();
		this.password = builder.getPassword();
		this.firstName = builder.getFirstName();
		this.lastName = builder.getLastName();
		this.deleted = builder.getDeleted();
		this.authorities = builder.getAuthorities();
	}
	
}
