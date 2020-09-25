package com.covidtrail.covidtrailbackend.repository;

import com.covidtrail.covidtrailbackend.model.PlacesVisitedLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Date;

@Service
public class PlacesVisitedLogService {
    @Autowired
    protected EntityManager manager;

    /**
     * Map object to Business Account Object
     *
     * @param obj - Object
     * @return Business Account
     */
    private PlacesVisitedLog mapToPlacesVisitedLog(Object obj) {
        Object[] val = (Object[]) obj;
        PlacesVisitedLog placesVisitedLog = new PlacesVisitedLog();

        placesVisitedLog.setId(Integer.parseInt(val[0].toString()));
        placesVisitedLog.setCreatedDate((Date) val[1]);
        placesVisitedLog.setLastModifiedDateTime((Date) val[2]);
        placesVisitedLog.setDeletedDateTime((Date) val[3]);
        placesVisitedLog.setDeleted(Integer.parseInt(val[4].toString()));
        placesVisitedLog.setUserAccountId(Integer.parseInt(val[5].toString()));
        placesVisitedLog.setBusinessAccountId(Integer.parseInt(val[6].toString()));
        placesVisitedLog.setVisitedDateTime((Date) val[7]);

        return placesVisitedLog;
    }
}
