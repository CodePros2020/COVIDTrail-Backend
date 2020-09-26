package com.covidtrail.covidtrailbackend.repository;

import com.covidtrail.covidtrailbackend.dto.BusinessAccountDto;
import com.covidtrail.covidtrailbackend.dto.PlacesVisitedLogDto;
import com.covidtrail.covidtrailbackend.dto.UserAccountDto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<PlacesVisitedLogDto> getPlacesVisitedLogsByUserId(int userId) {
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

        return (List<PlacesVisitedLogDto>) query.getResultList().stream()
                .map(this::mapToPlacesVisitedLog).collect(Collectors.toList());
    }

    /**
     * Get the list of places visited logs by business id
     *
     * @return list of places visited logs
     */
    public List<PlacesVisitedLogDto> getPlacesVisitedLogsByBusinessId(int businessId) {
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

        return (List<PlacesVisitedLogDto>) query.getResultList().stream()
                .map(this::mapToPlacesVisitedLog).collect(Collectors.toList());
    }

    /**
     * Create a new places visited log
     *
     * @param userId     - user account id
     * @param businessId - business account id
     * @return string message
     */
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
                " INSERT INTO PLACESVISITEDLOG (CREATED_DATETIME, LAST_MODIFIED_DATETIME, DELETED, VISITED_DATETIME, USERACCOUNT_ID, BUSINESSACCOUNT_ID)" +
                " VALUES (GETDATE(), GETDATE(), 0, GETDATE(), :userId, :businessId)";

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
     */
    private PlacesVisitedLogDto mapToPlacesVisitedLog(Object obj) {
        Object[] val = (Object[]) obj;
        PlacesVisitedLogDto placesVisitedLogDto = new PlacesVisitedLogDto();

        placesVisitedLogDto.setId(Integer.parseInt(val[0].toString()));
        placesVisitedLogDto.setUserAccount(userAccountService.getUserAccountById(Integer.parseInt(val[1].toString())));
        placesVisitedLogDto.setBusinessAccount(businessAccountService.getBusinessAccountById(Integer.parseInt(val[2].toString())));
        placesVisitedLogDto.setVisitedDateTime((Date) val[3]);

        return placesVisitedLogDto;
    }
}
