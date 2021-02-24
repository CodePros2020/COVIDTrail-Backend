package com.covidtrail.covidtrailbackend.controller;

import com.covidtrail.covidtrailbackend.dto.LoginDto;
import com.covidtrail.covidtrailbackend.dto.PlacesVisitedLogDto;
import com.covidtrail.covidtrailbackend.repository.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
@Api(tags = { "Authentication" })
public class AuthenticationController {
    @Autowired
    protected AuthenticationService authenticationService;

    @PostMapping()
    @ApiOperation(value = "Log in.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public PlacesVisitedLogDto login(@RequestBody LoginDto loginDto) throws Exception {
        // TODO
        return null;
    }
}
