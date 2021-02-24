package com.covidtrail.covidtrailbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidtrail.covidtrailbackend.dto.AddressUpdateDto;
import com.covidtrail.covidtrailbackend.repository.AddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/address")
@Api(tags = { "Address Management" })
public class AddressController {

	@Autowired
	protected AddressService addressService;

	@PutMapping("/{id}/businessAccount")
    @ApiOperation(value = "Update a business account's address with id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String updateBusinessAccountAddress(@PathVariable int id, @RequestBody AddressUpdateDto request) throws Exception {
        try {
            return addressService.updateAddressById(id, request, true);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
	
	@PutMapping("/{id}/userAccount")
	@ApiOperation(value = "Update an user account's address with id.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Unexpected error")})
	public String updateUserAccountAddress(@PathVariable int id, @RequestBody AddressUpdateDto request) throws Exception {
		try {
			return addressService.updateAddressById(id, request, false);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
}
