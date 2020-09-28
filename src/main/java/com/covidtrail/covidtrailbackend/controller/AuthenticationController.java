package com.covidtrail.covidtrailbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidtrail.covidtrailbackend.dto.AccountDetailsDto;
import com.covidtrail.covidtrailbackend.repository.AuthenticationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/authentication")
@Api(tags = { "Authentication" })
public class AuthenticationController {
	@Autowired
	protected AuthenticationService authenticationService;

	@GetMapping("/success")
    @ApiOperation(value = "Logged in the user successfully.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public AccountDetailsDto success() throws Exception {
        return authenticationService.getLoggedInUserDetails();
    }
	
	@GetMapping("/logoutSuccess")
	@ApiOperation(value = "Logged in the user successfully.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Unexpected error")})
	public String logout() throws Exception {
		return "You have been logged out successfully.";
	}
	
}
