package com.covidtrail.covidtrailbackend.controller;

import com.covidtrail.covidtrailbackend.dto.PlacesVisitedLogDto;
import com.covidtrail.covidtrailbackend.model.BusinessAccount;
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

    @GetMapping()
    @ApiOperation(value = "Get a list of all places visited logs.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public List<PlacesVisitedLogDto> getAllPlacesVisitedLogs() throws Exception {
        return placesVisitedLogService.getAllPlacesVisitedLogs();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an places visited log by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public PlacesVisitedLogDto getPlacesVisitedLogById(@PathVariable int id) {
        return placesVisitedLogService.getPlacesVisitedLogById(id);
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "Get an places visited log by user id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public List<PlacesVisitedLogDto> getPlacesVisitedLogsByUserId(@PathVariable int userId) throws Exception {
        return placesVisitedLogService.getPlacesVisitedLogsByUserId(userId);
    }

    @GetMapping("/{businessId}")
    @ApiOperation(value = "Get an places visited log by business id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public List<PlacesVisitedLogDto> getPlacesVisitedLogsByBusinessId(@PathVariable int businessId) throws Exception {
        return placesVisitedLogService.getPlacesVisitedLogsByBusinessId(businessId);
    }

    @PostMapping()
    @ApiOperation(value = "Create a new places visited log.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String createPlacesVisitedLog(@RequestBody BusinessAccount userAccount) throws Exception {
        // TODO
        return null;
    }
}
