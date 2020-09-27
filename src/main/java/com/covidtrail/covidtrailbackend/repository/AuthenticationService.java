package com.covidtrail.covidtrailbackend.repository;

import com.covidtrail.covidtrailbackend.config.SessionInfo;
import com.covidtrail.covidtrailbackend.dto.AccountDetailsDto;
import com.covidtrail.covidtrailbackend.model.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
public class AuthenticationService {
    @Autowired
    protected EntityManager manager;

    @Autowired
    protected SessionInfo sessionInfo;

    /**
     * Get logged in user's details
     *
     * @return Account Details
     */
    public AccountDetailsDto getLoggedInUserDetails() {
        CustomUser user = sessionInfo.getCurrentUser();

        String sql;

        if (user.getBusinessName() == null) {
            sql = "" +
                    " SELECT DISTINCT" +
                    "     u.ID," +
                    "     NULL," +
                    "     u.FIRSTNAME," +
                    "     u.MIDDLENAME," +
                    "     u.LASTNAME," +
                    "     a.ADDRESS_LINE_ONE," +
                    "     a.ADDRESS_LINE_TWO," +
                    "     a.CITY," +
                    "     a.PROVINCE," +
                    "     a.POSTAL_CODE," +
                    "     u.EMAIL," +
                    "     u.PHONE" +
                    " FROM USERACCOUNT u" +
                    "     LEFT JOIN ADDRESS a ON u.ADDRESS_ID = a.ID" +
                    " WHERE PHONE = :phone" +
                    "     AND u.DELETED = 0" +
                    " ORDER BY u.ID DESC";
        } else {
            sql = "" +
                    " SELECT DISTINCT" +
                    "     b.ID," +
                    "     b.BUSINESSNAME," +
                    "     NULL," +
                    "     NULL," +
                    "     NULL," +
                    "     a.ADDRESS_LINE_ONE," +
                    "     a.ADDRESS_LINE_TWO," +
                    "     a.CITY," +
                    "     a.PROVINCE," +
                    "     a.POSTAL_CODE," +
                    "     b.EMAIL," +
                    "     b.PHONE" +
                    " FROM BUSINESSACCOUNT b" +
                    "     LEFT JOIN ADDRESS a ON b.ADDRESS_ID = a.ID" +
                    " WHERE b.PHONE = :phone" +
                    "     AND b.DELETED = 0" +
                    " ORDER BY b.ID DESC";
        }

        Query query = manager.createNativeQuery(sql);

        query.setParameter("phone", user.getUsername());

        return mapToAccountDetailsDto(query.getSingleResult());
    }

    /**
     * Map object to Account Details Object
     *
     * @param obj - Object
     * @return Account Details
     */
    private AccountDetailsDto mapToAccountDetailsDto(Object obj) {
        Object[] val = (Object[]) obj;
        AccountDetailsDto accountDetails = new AccountDetailsDto();

        accountDetails.setId(Integer.parseInt(val[0].toString()));
        accountDetails.setBusinessName((String) val[1]);
        accountDetails.setFirstName((String) val[2]);
        accountDetails.setMiddleName((String) val[3]);
        accountDetails.setLastName((String) val[4]);
        accountDetails.setAddressLineOne((String) val[5]);
        accountDetails.setAddressLineTwo((String) val[6]);
        accountDetails.setCity((String) val[7]);
        accountDetails.setProvince((String) val[8]);
        accountDetails.setPostalCode((String) val[9]);
        accountDetails.setEmail((String) val[10]);
        accountDetails.setPhone((String) val[11]);

        return accountDetails;
    }
}
