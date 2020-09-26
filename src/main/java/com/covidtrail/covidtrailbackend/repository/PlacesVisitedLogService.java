package com.covidtrail.covidtrailbackend.repository;

import com.covidtrail.covidtrailbackend.dto.PlacesVisitedLogDto;
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
     * Get the list of all business accounts
     *
     * @return list of business account
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
     * Map object to Business Account Object
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
