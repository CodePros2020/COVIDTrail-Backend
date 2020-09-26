package com.covidtrail.covidtrailbackend.controller;

import com.covidtrail.covidtrailbackend.dto.BusinessAccountDto;
import com.covidtrail.covidtrailbackend.model.BusinessAccount;
import com.covidtrail.covidtrailbackend.repository.BusinessAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping()
    @ApiOperation(value = "Create a new business account.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public BusinessAccountDto createUserAccount(@RequestBody BusinessAccount userAccount) throws Exception {
        // TODO
        return null;
    }


    @PutMapping("/{id}/name")
    @ApiOperation(value = "Update an business names with id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String updateUserNamesById(@PathVariable int id, @RequestParam String newName) throws Exception {
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
    public String updateUserPhoneById(@PathVariable int id, @RequestParam String newPhone) throws Exception {
        try {
            return businessAccountService.updateBusinessPhoneById(id, newPhone);
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
