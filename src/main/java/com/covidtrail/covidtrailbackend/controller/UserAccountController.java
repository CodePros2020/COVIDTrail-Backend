package com.covidtrail.covidtrailbackend.controller;

import com.covidtrail.covidtrailbackend.dto.UserAccountDto;
import com.covidtrail.covidtrailbackend.dto.UserAccountNameUpdateDto;
import com.covidtrail.covidtrailbackend.model.UserAccount;
import com.covidtrail.covidtrailbackend.repository.UserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userAccount")
@Api(tags = { "User Account" })
public class UserAccountController {
    @Autowired
    protected UserAccountService userAccountService;

    @GetMapping("/")
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

    @PostMapping("/")
    @ApiOperation(value = "Create a new business account.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public UserAccountDto createUserAccount(@RequestBody UserAccount userAccount) throws Exception {
        // TODO
        return null;
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
    @ApiOperation(value = "Update an user's email address with id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String updateUserPhoneById(@PathVariable int id, @RequestParam String newPhone) throws Exception {
        try {
            return userAccountService.updateUserPhoneById(id, newPhone);
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
