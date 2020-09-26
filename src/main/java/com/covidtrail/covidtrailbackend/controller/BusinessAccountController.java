package com.covidtrail.covidtrailbackend.controller;

import com.covidtrail.covidtrailbackend.model.BusinessAccount;
import com.covidtrail.covidtrailbackend.model.UserAccount;
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

    @GetMapping("/")
    @ApiOperation(value = "Get a list of all business accounts.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public List<BusinessAccount> getAllBusinessAccounts() throws Exception {
        return businessAccountService.getAllBusinessAccounts();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an business account by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public BusinessAccount getBusinessAccountById(@PathVariable int id) throws Exception {
        // TODO
        return null;
    }

    @PostMapping("/")
    @ApiOperation(value = "Create a new business account.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public BusinessAccount createUserAccount(@RequestBody BusinessAccount userAccount) throws Exception {
        // TODO
        return null;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a business account with id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public BusinessAccount updateUserAccount(@RequestBody BusinessAccount userAccount) throws Exception {
        // TODO
        return null;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an business account by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public BusinessAccount deleteBusinessAccountById(@PathVariable int id) throws Exception {
        // TODO
        return null;
    }
}
