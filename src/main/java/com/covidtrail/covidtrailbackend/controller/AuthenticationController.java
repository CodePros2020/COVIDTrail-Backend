package com.covidtrail.covidtrailbackend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/authentication")
@Api(tags = { "Authentication" })
public class AuthenticationController {

	@GetMapping("/success")
    @ApiOperation(value = "Logged in the user successfully.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String success() throws Exception {
        return "success";
    }

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model,HttpServletRequest request) {

	    String referrer = request.getHeader("Referer");
	    if(referrer!=null){
	        request.getSession().setAttribute("url_prior_login", referrer);
	    }
	    
	    return "redirect:/login";
	}
	
}
