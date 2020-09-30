package com.covidtrail.covidtrailbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covidtrail.covidtrailbackend.dto.BusinessAccountCreateDto;
import com.covidtrail.covidtrailbackend.dto.BusinessAccountDto;
import com.covidtrail.covidtrailbackend.repository.BusinessAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/businessAccount")
@Api(tags = { "Business Account" })
public class BusinessAccountController {
    @Autowired
    protected BusinessAccountService businessAccountService;

    @GetMapping()
    @ApiOperation(value = "Get a list of all business accounts.")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Success"),
    		@ApiResponse(code = 500, message = "Unexpected error")})
    public List<BusinessAccountDto> getAllBusinessAccounts() throws Exception {
    	return businessAccountService.getAllBusinessAccounts();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an business account by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public BusinessAccountDto getBusinessAccountById(@PathVariable int id) throws Exception {
        return businessAccountService.getBusinessAccountById(id);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create a new business account.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String createBusinessAccount(@RequestBody BusinessAccountCreateDto dto) throws Exception {
        return businessAccountService.createBusinessAccount(dto);
    }

    @PutMapping("/{id}/name/{newName}")
    @ApiOperation(value = "Update an business names with id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String updateUserNamesById(@PathVariable int id, @PathVariable String newName) throws Exception {
        try {
            return businessAccountService.updateBusinessNamesById(id, newName);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping("/{id}/email")
    @ApiOperation(value = "Update an business email address with id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String updateUserEmailById(@PathVariable int id, @RequestParam String newEmail) throws Exception {
        try {
            return businessAccountService.updateBusinessEmailById(id, newEmail);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping("/{id}/phone")
    @ApiOperation(value = "Update an business phone number with id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String updateUserPhoneById(@PathVariable int id, @RequestParam String newPhone, @RequestParam String password) throws Exception {
        try {
            return businessAccountService.updateBusinessPhoneById(id, newPhone, password);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an business account by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String deleteBusinessAccountById(@PathVariable int id) throws Exception {
        return businessAccountService.deleteBusinessAccountById(id);
    }
}
