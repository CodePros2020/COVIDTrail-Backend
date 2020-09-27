package com.covidtrail.covidtrailbackend.controller;

import com.covidtrail.covidtrailbackend.dto.PlacesVisitedLogBusinessDto;
import com.covidtrail.covidtrailbackend.dto.PlacesVisitedLogDto;
import com.covidtrail.covidtrailbackend.dto.PlacesVisitedLogUserDto;
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
@Api(tags = {"Places Visited Log"})
public class PlacesVisitedLogController {
    @Autowired
    protected PlacesVisitedLogService placesVisitedLogService;

    @GetMapping()
    @ApiOperation(value = "Get a list of all places visited logs.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public List<PlacesVisitedLogDto> getAllPlacesVisitedLogs() {
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

    @GetMapping("/{userId}/user")
    @ApiOperation(value = "Get an places visited log by user id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public List<PlacesVisitedLogBusinessDto> getPlacesVisitedLogsByUserId(@PathVariable int userId) {
        return placesVisitedLogService.getPlacesVisitedLogsByUserId(userId);
    }

    @GetMapping("/{businessId}/business")
    @ApiOperation(value = "Get an places visited log by business id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public List<PlacesVisitedLogUserDto> getPlacesVisitedLogsByBusinessId(@PathVariable int businessId) {
        return placesVisitedLogService.getPlacesVisitedLogsByBusinessId(businessId);
    }

    @PostMapping()
    @ApiOperation(value = "Create a new places visited log.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Unexpected error")})
    public String createPlacesVisitedLog(@RequestParam int userId, @RequestParam int businessId) throws Exception {
        try {
            return placesVisitedLogService.createPlacesVisitedLog(userId, businessId);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
