package com.covidtrail.covidtrailbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
				"/webjars/**"};

		http.cors();
		http.csrf().disable()

		.authorizeRequests()
		.antMatchers(noAuthPaths).permitAll()
		.and()
			.authorizeRequests()
			.anyRequest().authenticated()
		.and()
			.formLogin().permitAll()
		.and()
			.logout().permitAll();
	}

}
