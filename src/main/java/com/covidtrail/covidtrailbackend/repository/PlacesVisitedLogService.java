package com.covidtrail.covidtrailbackend.repository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.covidtrail.covidtrailbackend.dto.BusinessAccountDto;
import com.covidtrail.covidtrailbackend.dto.PlacesVisitedLogBusinessDatailsDto;
import com.covidtrail.covidtrailbackend.dto.PlacesVisitedLogBusinessDto;
import com.covidtrail.covidtrailbackend.dto.PlacesVisitedLogDto;
import com.covidtrail.covidtrailbackend.dto.PlacesVisitedLogUserDetailsDto;
import com.covidtrail.covidtrailbackend.dto.PlacesVisitedLogUserDto;
import com.covidtrail.covidtrailbackend.dto.UserAccountDto;
import com.covidtrail.covidtrailbackend.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class PlacesVisitedLogService {
    @Autowired
    protected EntityManager manager;

    @Autowired
    protected UserAccountService userAccountService;

    @Autowired
    protected BusinessAccountService businessAccountService;

    /**
     * Get the list of all places visited logs
     *
     * @return list of places visited logs
     */
    public List<PlacesVisitedLogDto> getAllPlacesVisitedLogs() {
        String sql = "" +
                " SELECT DISTINCT" +
                "     ID," +
                "     VISITED_DATETIME," +
                "     USERACCOUNT_ID," +
                "     BUSINESSACCOUNT_ID" +
                " FROM PLACESVISITEDLOG" +
                " WHERE DELETED = 0" +
                " ORDER BY ID DESC";

        Query query = manager.createNativeQuery(sql);

        return (List<PlacesVisitedLogDto>) query.getResultList().stream()
                .map(this::mapToPlacesVisitedLog).collect(Collectors.toList());
    }

    /**
     * Get places visited logs by id
     *
     * @return places visited logs
     */
    public PlacesVisitedLogDto getPlacesVisitedLogById(int id) {
        String sql = "" +
                " SELECT DISTINCT" +
                "     ID," +
                "     VISITED_DATETIME," +
                "     USERACCOUNT_ID," +
                "     BUSINESSACCOUNT_ID" +
                " FROM PLACESVISITEDLOG" +
                " WHERE ID = :id" +
                "     AND DELETED = 0" +
                " ORDER BY ID DESC";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("id", id);

        return mapToPlacesVisitedLog(query.getSingleResult());
    }

    /**
     * Get the list of places visited logs by user id
     *
     * @return list of places visited logs
     */
    public List<PlacesVisitedLogBusinessDto> getPlacesVisitedLogsByUserId(int userId) {
        String sql = "" +
                " SELECT DISTINCT" +
                "     ID," +
                "     VISITED_DATETIME," +
                "     USERACCOUNT_ID," +
                "     BUSINESSACCOUNT_ID" +
                " FROM PLACESVISITEDLOG" +
                " WHERE USERACCOUNT_ID = :userId" +
                "     AND DELETED = 0" +
                " ORDER BY ID DESC";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("userId", userId);

        List<PlacesVisitedLogBusinessDatailsDto> businessDetailsDtoList;
        PlacesVisitedLogBusinessDatailsDto businessDetailsTemp;

        for (PlacesVisitedLogDto dto : queryResult) {
            String visitedDate = dto.getVisitedDate();
            int i = 0;

            PlacesVisitedLogBusinessDto temp = null;

            for (PlacesVisitedLogBusinessDto d : result) {
                if (d.getVisitedDate().equals(visitedDate)) {
                    temp = d;
                    break;
                }

                i++;
            }

            if (temp == null) {
                temp = new PlacesVisitedLogBusinessDto();
                businessDetailsDtoList = new ArrayList<>();
                businessDetailsTemp = new PlacesVisitedLogBusinessDatailsDto();

                businessDetailsTemp.setId(dto.getId());
                businessDetailsTemp.setVisitedDateTime(dto.getVisitedDateTime());
                businessDetailsTemp.setBusinessAccount(dto.getBusinessAccount());
                businessDetailsDtoList.add(businessDetailsTemp);

                temp.setVisitedDate(visitedDate);
                temp.setBusinessDetails(businessDetailsDtoList);

                result.add(temp);
            } else {
                businessDetailsDtoList = temp.getBusinessDetails();
                businessDetailsTemp = new PlacesVisitedLogBusinessDatailsDto();

                businessDetailsTemp.setId(dto.getId());
                businessDetailsTemp.setVisitedDateTime(dto.getVisitedDateTime());
                businessDetailsTemp.setBusinessAccount(dto.getBusinessAccount());
                businessDetailsDtoList.add(businessDetailsTemp);

                temp.setVisitedDate(visitedDate);
                temp.setBusinessDetails(businessDetailsDtoList);

                result.set(i, temp);
            }
        }

        return result;
    }

    /**
     *
     * @return list of places visited logs
     */
    public List<PlacesVisitedLogUserDto> getPlacesVisitedLogsByBusinessId(int businessId) {
        String sql = "" +
                " SELECT DISTINCT" +
                "     ID," +
                "     VISITED_DATETIME," +
                "     USERACCOUNT_ID," +
                "     BUSINESSACCOUNT_ID" +
                " FROM PLACESVISITEDLOG" +
                " WHERE BUSINESSACCOUNT_ID = :businessId" +
                "     AND DELETED = 0" +
                " ORDER BY ID DESC";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("businessId", businessId);

        List<PlacesVisitedLogDto> queryResult = (List<PlacesVisitedLogDto>) query.getResultList().stream()
                .map(this::mapToPlacesVisitedLog).collect(Collectors.toList());

        List<PlacesVisitedLogUserDto> result = new ArrayList<>();
        List<PlacesVisitedLogUserDetailsDto> userDetailsDtoList;
        PlacesVisitedLogUserDetailsDto userDetailsTemp;

        for (PlacesVisitedLogDto dto : queryResult) {
            String visitedDate = dto.getVisitedDate();
            int i = 0;

            PlacesVisitedLogUserDto temp = null;

            for (PlacesVisitedLogUserDto d : result) {
                if (d.getVisitedDate().equals(visitedDate)) {
                    temp = d;
                    break;
                }

                i++;
            }

            if (temp == null) {
                temp = new PlacesVisitedLogUserDto();
                userDetailsDtoList = new ArrayList<>();
                userDetailsTemp = new PlacesVisitedLogUserDetailsDto();

                userDetailsTemp.setId(dto.getId());
                userDetailsTemp.setVisitedDateTime(dto.getVisitedDateTime());
                userDetailsTemp.setUserId(dto.getUserAccount().getId());
                userDetailsTemp.setFirstName(dto.getUserAccount().getFirstName());
                userDetailsTemp.setMiddleName(dto.getUserAccount().getMiddleName());
                userDetailsTemp.setLastName(dto.getUserAccount().getLastName());
                userDetailsDtoList.add(userDetailsTemp);

                temp.setVisitedDate(visitedDate);
                temp.setUserDetails(userDetailsDtoList);

                result.add(temp);
            } else {
                userDetailsDtoList = temp.getUserDetails();
                userDetailsTemp = new PlacesVisitedLogUserDetailsDto();

                userDetailsTemp.setId(dto.getId());
                userDetailsTemp.setVisitedDateTime(dto.getVisitedDateTime());
                userDetailsTemp.setUserId(dto.getUserAccount().getId());
                userDetailsTemp.setFirstName(dto.getUserAccount().getFirstName());
                userDetailsTemp.setMiddleName(dto.getUserAccount().getMiddleName());
                userDetailsTemp.setLastName(dto.getUserAccount().getLastName());
                userDetailsDtoList.add(userDetailsTemp);

                temp.setVisitedDate(visitedDate);
                temp.setUserDetails(userDetailsDtoList);

                result.set(i, temp);
            }
        }

        return result;
    }

    /**
     * Create a new places visited log
     *
     * @param userId     - user account id
     * @param businessId - business account id
     * @return string message
     */
    @Transactional
    public String createPlacesVisitedLog(int userId, int businessId) throws Exception {
        UserAccountDto userAccount = userAccountService.getUserAccountById(userId);
        BusinessAccountDto businessAccount = businessAccountService.getBusinessAccountById(businessId);

        if (userAccount == null) {
            throw new NotFoundException("User account not found with the id " + userId);
        }

        if (businessAccount == null) {
            throw new NotFoundException("Business account not found with the id " + businessId);
        }

        String sql = "" +
                " INSERT INTO PLACESVISITEDLOG (CREATED_DATETIME, DELETED, VISITED_DATETIME, USERACCOUNT_ID, BUSINESSACCOUNT_ID)" +
                " VALUES (GETDATE(), 0, GETDATE(), :userId, :businessId)";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("userId", userId);
        query.setParameter("businessId", businessId);

        query.executeUpdate();

        return String.format("Places visit log has been successfully created between user %d and business %d.", userId, businessId);
    }

    /**
     * Map object to Places Visited Log Object
     *
     * @param obj - Object
     * @return Business Account
     * @throws ParseException 
     */
    private PlacesVisitedLogDto mapToPlacesVisitedLog(Object obj) {
        Object[] val = (Object[]) obj;
        PlacesVisitedLogDto placesVisitedLogDto = new PlacesVisitedLogDto();

        placesVisitedLogDto.setId(Integer.parseInt(val[0].toString()));
        
        try {
			
        	Date visitedDateTime = (Date) val[1];
        	String formatedVisitedDate = DateUtils
        			.to_YYYY_MM_DD(visitedDateTime)
        			.toET()
        			.getFormatedDate();
        	
        	placesVisitedLogDto.setVisitedDate(formatedVisitedDate);
        	
        	String formatedVisitedDateTime = DateUtils
        			.to_YYYY_MM_DD_HH_MM(visitedDateTime)
        			.toET()
        			.getFormatedDate();
        	
        	placesVisitedLogDto.setVisitedDateTime(formatedVisitedDateTime);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
        
        placesVisitedLogDto.setUserAccount(userAccountService.getUserAccountById(Integer.parseInt(val[2].toString())));
        placesVisitedLogDto.setBusinessAccount(businessAccountService.getBusinessAccountById(Integer.parseInt(val[3].toString())));

        return placesVisitedLogDto;
    }
}
