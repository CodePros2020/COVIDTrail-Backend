package com.covidtrail.covidtrailbackend.controller;

import com.covidtrail.covidtrailbackend.model.BusinessAccount;
import com.covidtrail.covidtrailbackend.model.PlacesVisitedLog;
import com.covidtrail.covidtrailbackend.repository.BusinessAccountService;
import com.covidtrail.covidtrailbackend.repository.PlacesVisitedLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/placesVisitedLog")
@Api(tags = { "Places Visited Log" })
public class PlacesVisitedLogController {
    @Autowired
    protected PlacesVisitedLogService placesVisitedLogService;

    @GetMapping("/")
    @ApiOperation(value = "Get a list of all places visited logs.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public List<PlacesVisitedLog> getAllPlacesVisitedLogs() throws Exception {
        // TODO
        return null;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an places visited log by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public PlacesVisitedLog getPlacesVisitedLogById(@PathVariable int id) throws Exception {
        // TODO
        return null;
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "Get an places visited log by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public PlacesVisitedLog getPlacesVisitedLogByUserId(@PathVariable int userId) throws Exception {
        // TODO
        return null;
    }

    @GetMapping("/{businessId}")
    @ApiOperation(value = "Get an places visited log by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public PlacesVisitedLog getPlacesVisitedLogByBusinessId(@PathVariable int businessId) throws Exception {
        // TODO
        return null;
    }


    @PostMapping("/")
    @ApiOperation(value = "Create a new places visited log.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public PlacesVisitedLog createPlacesVisitedLog(@RequestBody BusinessAccount userAccount) throws Exception {
        // TODO
        return null;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a places visited log with id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public PlacesVisitedLog updatePlacesVisitedLog(@RequestBody BusinessAccount userAccount) throws Exception {
        // TODO
        return null;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an places visited log by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public PlacesVisitedLog deletePlacesVisitedLogById(@PathVariable int id) throws Exception {
        // TODO
        return null;
    }
}
