package com.covidtrail.covidtrailbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covidtrail.covidtrailbackend.config.SessionInfo;
import com.covidtrail.covidtrailbackend.model.CustomUser;
import com.covidtrail.covidtrailbackend.repository.ManageUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/manageUser")
@Api(tags = { "Manage User" })
public class ManageUserController {

	@Autowired
    protected SessionInfo sessionInfo;
	
	@Autowired
	protected ManageUserService manageUserService;
	
	@PutMapping()
    @ApiOperation(value = "Updates an existing password.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String updatePassword(@RequestParam String password) throws Exception {
		
		CustomUser customUser = (CustomUser) sessionInfo.getCurrentUser();
		String businessName = customUser.getBusinessName();
		Boolean isBusiness = businessName != null && !businessName.isEmpty();
		
        return manageUserService.updatePassword(password, isBusiness, customUser.getUsername());
    }
	
}
