package com.covidtrail.covidtrailbackend.repository;

import com.covidtrail.covidtrailbackend.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAccountService {
    @Autowired
    protected EntityManager manager;

    public List<UserAccount> getUserAccountById() {
        String sql = "" +
                "SELECT * " +
                "FROM UserAccount " +
                "ORDER BY ID DESC";

        Query query = manager.createNativeQuery(sql);

        return (List<UserAccount>) query.getResultList().stream()
                .map(x -> mapToUserAccount(x)).collect(Collectors.toList());
    }

    public UserAccount mapToUserAccount(Object obj) {
        Object[] val = (Object[]) obj;
        UserAccount userAccount = new UserAccount();

        userAccount.setId(Integer.parseInt(val[0].toString()));
        userAccount.setCreatedDate((Date) val[1]);
        userAccount.setLastModifiedDateTime((Date) val[2]);
        userAccount.setDeletedDateTime((Date) val[3]);
        userAccount.setDeleted(Integer.parseInt(val[4].toString()));
        userAccount.setFirstName((String) val[5]);
        userAccount.setMiddleName((String) val[6]);
        userAccount.setLastName((String) val[7]);
        userAccount.setAddressLineOne((String) val[8]);
        userAccount.setAddressLineTwo((String) val[9]);
        userAccount.setCity((String) val[10]);
        userAccount.setProvince((String) val[11]);
        userAccount.setPostalCode((String) val[12]);
        userAccount.setEmail((String) val[13]);
        userAccount.setPhone((String) val[14]);
        userAccount.setPassword((String) val[15]);

        return userAccount;
    }
}
