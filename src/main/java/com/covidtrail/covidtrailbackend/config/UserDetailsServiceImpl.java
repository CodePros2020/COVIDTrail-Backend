package com.covidtrail.covidtrailbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.covidtrail.covidtrailbackend.repository.CustomUserService;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CustomUserService customUserService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return customUserService.findByUserName(username);
	}

}
