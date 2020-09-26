package com.covidtrail.covidtrailbackend.repository;

import com.covidtrail.covidtrailbackend.model.BusinessAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessAccountService {
    @Autowired
    protected EntityManager manager;

    /**
     * Get the list of all business accounts
     *
     * @return list of business account
     */
    public List<BusinessAccount> getAllBusinessAccounts() {
        String sql = "" +
                " SELECT DISTINCT *" +
                " FROM BUSINESSACCOUNT" +
                " WHERE DELETED = 0" +
                " ORDER BY ID DESC";

        Query query = manager.createNativeQuery(sql);

        return (List<BusinessAccount>) query.getResultList().stream()
                .map(this::mapToBusinessAccount).collect(Collectors.toList());
    }

    /**
     * Map object to Business Account Object
     *
     * @param obj - Object
     * @return Business Account
     */
    private BusinessAccount mapToBusinessAccount(Object obj) {
        Object[] val = (Object[]) obj;
        BusinessAccount businessAccount = new BusinessAccount();

        businessAccount.setId(Integer.parseInt(val[0].toString()));
        businessAccount.setCreatedDate((Date) val[1]);
        businessAccount.setLastModifiedDateTime((Date) val[2]);
        businessAccount.setDeletedDateTime((Date) val[3]);
        businessAccount.setDeleted(Integer.parseInt(val[4].toString()));
        businessAccount.setBusinessName((String) val[5]);
        businessAccount.setAddressLineOne((String) val[6]);
        businessAccount.setAddressLineTwo((String) val[7]);
        businessAccount.setCity((String) val[8]);
        businessAccount.setProvince((String) val[9]);
        businessAccount.setPostalCode((String) val[10]);
        businessAccount.setEmail((String) val[11]);
        businessAccount.setPhone((String) val[12]);
        businessAccount.setPassword((String) val[13]);

        return businessAccount;
    }
}
