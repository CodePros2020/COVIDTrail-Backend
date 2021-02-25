package com.covidtrail.covidtrailbackend.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.covidtrail.covidtrailbackend.dto.AccountDetailsDto;
import com.covidtrail.covidtrailbackend.model.CustomUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	protected SessionInfo sessionInfo;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	};

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] noAuthPaths = {
				"/swagger-ui.html",
				"/v2/api-docs",
				"/configuration/ui/**",
				"/swagger-resources/**",
				"/configuration/security/**",
				"/swagger-ui.html",
				"/webjars/**",
				"/api/authentication/success",
				"/api/businessAccount/create",
				"/api/userAccount/create"};

		http.cors();
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(noAuthPaths).permitAll()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			.and()
			.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.formLogin().permitAll().successHandler(this::loginSuccessHandler)
			.and()
			.logout()
			.logoutSuccessUrl("/api/authentication/logoutSuccess")
			.permitAll()
			.and()
			.httpBasic();

		http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
				response.isCommitted();
				response.sendError(403, "Forbidden");
			}
		});
	}

	private void loginSuccessHandler(
			HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication) throws IOException {

		CustomUser customUser = sessionInfo.getCurrentUser();
		AccountDetailsDto accountDetails = new AccountDetailsDto();
		accountDetails.setFirstName(customUser.getFirstName());
		accountDetails.setLastName(customUser.getLastName());
		accountDetails.setBusinessName(customUser.getBusinessName());
		accountDetails.setId(customUser.getId());
		accountDetails.setPhone(customUser.getUsername());
		accountDetails.setEmail(customUser.getEmail());
		accountDetails.setAddressLineOne(customUser.getAddressLineOne());
		accountDetails.setAddressLineTwo(customUser.getAddressLineTwo());
		accountDetails.setCity(customUser.getCity());
		accountDetails.setProvince(customUser.getProvince());
		accountDetails.setPostalCode(customUser.getPostalCode());
		accountDetails.setToken(customUser.getToken());
		
		response.setStatus(HttpStatus.OK.value());
		objectMapper.writeValue(response.getWriter(), accountDetails);
	}
}
