package com.covidtrail.covidtrailbackend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("")
@Api(tags = { "Swagger Auth" })
public class SwaggerLoginController {

	@ApiOperation("Login.")
	@PostMapping("/login")
	public void swaggerLogin(@ApiParam("username") @RequestParam String username, @ApiParam("password") @RequestParam String password) {
	    throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
	}

	@ApiOperation("Logout.")
	@PostMapping("/logout")
	public void swaggerLogout() {
	    throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
	}
	
}
