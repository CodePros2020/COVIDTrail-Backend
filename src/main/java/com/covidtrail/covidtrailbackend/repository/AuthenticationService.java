package com.covidtrail.covidtrailbackend.repository;

import javax.management.Query;
import javax.persistence.EntityManager;

import com.covidtrail.covidtrailbackend.dto.AccountDetailsDto;
import com.covidtrail.covidtrailbackend.model.CustomUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    protected EntityManager manager;

    /**
     * Get logged in user's details
     *
     * @return Account Details
     */
    public AccountDetailsDto getLoggedInUserDetails() {
        CustomUser user = sessionInfo.getCurrentUser();

        String sql;

        Boolean isBusiness = false;

        if (user.getBusinessName() == null) {
            sql = "" + " SELECT DISTINCT" + "     u.ID," + "     NULL," + "     u.FIRSTNAME," + "     u.MIDDLENAME,"
                    + "     u.LASTNAME," + "     a.ADDRESS_LINE_ONE," + "     a.ADDRESS_LINE_TWO," + "     a.CITY,"
                    + "     a.PROVINCE," + "     a.POSTAL_CODE," + "     u.EMAIL," + "     u.PHONE"
                    + " FROM USERACCOUNT u" + "     LEFT JOIN ADDRESS a ON u.ADDRESS_ID = a.ID"
                    + " WHERE PHONE = :phone" + "     AND u.DELETED = 0" + " ORDER BY u.ID DESC";
        } else {

            isBusiness = true;

            sql = "" + " SELECT DISTINCT" + "     b.ID," + "     b.BUSINESSNAME," + "     a.ADDRESS_LINE_ONE,"
                    + "     a.ADDRESS_LINE_TWO," + "     a.CITY," + "     a.PROVINCE," + "     a.POSTAL_CODE,"
                    + "     b.EMAIL," + "     b.PHONE" + " FROM BUSINESSACCOUNT b"
                    + "     LEFT JOIN ADDRESS a ON b.ADDRESS_ID = a.ID" + " WHERE b.PHONE = :phone"
                    + "     AND b.DELETED = 0" + " ORDER BY b.ID DESC";
        }

        Query query = manager.createNativeQuery(sql);
        query.setParameter("phone", user.getUsername());

        Object obj = query.getSingleResult();

        if (isBusiness) {
            return mapToBusinessAccountDetailsDto(obj);
        } else {
            return mapToUserAccountDetailsDto(obj);
        }
    }

    /**
     * Map object to User Account Details Object
     *
     * @param obj - Object
     * @return Account Details
     */
    private AccountDetailsDto mapToUserAccountDetailsDto(Object obj) {
        Object[] val = (Object[]) obj;
        AccountDetailsDto accountDetails = new AccountDetailsDto();

        accountDetails.setId(Integer.parseInt(val[0].toString()));
        accountDetails.setFirstName((String) val[1]);
        accountDetails.setMiddleName((String) val[2]);
        accountDetails.setLastName((String) val[3]);
        accountDetails.setAddressLineOne((String) val[4]);
        accountDetails.setAddressLineTwo((String) val[5]);
        accountDetails.setCity((String) val[6]);
        accountDetails.setProvince((String) val[7]);
        accountDetails.setPostalCode((String) val[8]);
        accountDetails.setEmail((String) val[9]);
        accountDetails.setPhone((String) val[10]);

        return accountDetails;
    }

    /**
     * Map object to Business Account Details Object
     *
     * @param obj - Object
     * @return Account Details
     */
    private AccountDetailsDto mapToBusinessAccountDetailsDto(Object obj) {
        Object[] val = (Object[]) obj;
        AccountDetailsDto accountDetails = new AccountDetailsDto();

        accountDetails.setId(Integer.parseInt(val[0].toString()));
        accountDetails.setBusinessName((String) val[1]);
        accountDetails.setAddressLineOne((String) val[2]);
        accountDetails.setAddressLineTwo((String) val[3]);
        accountDetails.setCity((String) val[4]);
        accountDetails.setProvince((String) val[5]);
        accountDetails.setPostalCode((String) val[6]);
        accountDetails.setEmail((String) val[7]);
        accountDetails.setPhone((String) val[8]);

        return accountDetails;
    }
}
