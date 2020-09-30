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

import com.covidtrail.covidtrailbackend.dto.UserAccountCreateDto;
import com.covidtrail.covidtrailbackend.dto.UserAccountDto;
import com.covidtrail.covidtrailbackend.dto.UserAccountNameUpdateDto;
import com.covidtrail.covidtrailbackend.repository.UserAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/userAccount")
@Api(tags = { "User Account" })
public class UserAccountController {
    @Autowired
    protected UserAccountService userAccountService;

    @GetMapping()
    @ApiOperation(value = "Get a list of all user accounts.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public List<UserAccountDto> getAllUserAccounts() {
        return userAccountService.getAllUserAccounts();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an user account by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public UserAccountDto getUserAccountById(@PathVariable int id) throws Exception {
        return userAccountService.getUserAccountById(id);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create a new user account.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String createUserAccount(@RequestBody UserAccountCreateDto dto) throws Exception {
    	return userAccountService.createUserAccount(dto);
    }

    @PutMapping("/{id}/name")
    @ApiOperation(value = "Update an user's names with id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String updateUserNamesById(@PathVariable int id, @RequestBody UserAccountNameUpdateDto request) throws Exception {
        try {
            return userAccountService.updateUserNamesById(id, request);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping("/{id}/email")
    @ApiOperation(value = "Update an user's email address with id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String updateUserEmailById(@PathVariable int id, @RequestParam String newEmail) throws Exception {
        try {
            return userAccountService.updateUserEmailById(id, newEmail);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping("/{id}/phone")
    @ApiOperation(value = "Update an user's phone address with id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String updateUserPhoneById(@PathVariable int id, @RequestParam String newPhone, @RequestParam String password) throws Exception {
        try {
            return userAccountService.updateUserPhoneById(id, newPhone, password);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an user account by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String deleteUserAccountById(@PathVariable int id) throws Exception {
        try {
            return userAccountService.deleteUserAccountById(id);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
