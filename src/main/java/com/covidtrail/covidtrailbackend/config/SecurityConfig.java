package com.covidtrail.covidtrailbackend.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

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

		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setTargetUrlParameter("redirectTo");
		successHandler.setDefaultTargetUrl("/api/authentication/success");
		successHandler.setAlwaysUseDefaultTargetUrl(true);
		
		http.cors();
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers(noAuthPaths).permitAll()
		.and()
			.authorizeRequests()
			.anyRequest().authenticated()
		.and()
			.formLogin().permitAll()
			.successHandler(successHandler)
		.and()
			.logout()
			.logoutSuccessUrl("/api/authentication/success")
			.permitAll();
		
		http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {

	        @Override
	        public void commence(HttpServletRequest request, HttpServletResponse response,
	                AuthenticationException authException) throws IOException, ServletException {
	        	response.isCommitted();
	        	response.sendError(403, "Forbidden");
	        }
	    });
		
		
	}
	

}
