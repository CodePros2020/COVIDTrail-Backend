package com.covidtrail.covidtrailbackend.controller;

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
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public List<UserAccount> getAllUserAccounts() throws Exception {
        return userAccountService.getAllUserAccounts();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an user account by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public UserAccount getUserAccountById(@PathVariable int id) throws Exception {
        return userAccountService.getUserAccountById(id);
    }

    @PostMapping("/")
    @ApiOperation(value = "Create a new business account.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public UserAccount createUserAccount(@RequestBody UserAccount userAccount) throws Exception {
        // TODO
        return null;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an user account with id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public UserAccount updateUserAccount(@RequestBody UserAccount userAccount) throws Exception {
        // TODO
        return null;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an user account by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String deleteUserAccountById(@PathVariable int id) throws Exception {
        return userAccountService.deleteUserAccountById(id);
    }
}
