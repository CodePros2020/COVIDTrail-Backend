package com.covidtrail.covidtrailbackend.config;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.covidtrail.covidtrailbackend.model.CustomUser;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionInfo {
	public CustomUser getCurrentUser() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		CustomUser user = null;
		if(null != securityContext.getAuthentication()){
			user = (CustomUser) securityContext.getAuthentication().getPrincipal();
		}	
		return user;
	}
}
