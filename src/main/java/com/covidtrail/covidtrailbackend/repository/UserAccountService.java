package com.covidtrail.covidtrailbackend.repository;

import com.covidtrail.covidtrailbackend.model.UserAccount;
import javassist.NotFoundException;
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

    /**
     * Get the list of all user accounts
     *
     * @return list of user accounts
     */
    public List<UserAccount> getAllUserAccounts() {
        String sql = "" +
                " SELECT DISTINCT *" +
                " FROM USERACCOUNT" +
                " WHERE DELETED = 0" +
                " ORDER BY ID DESC";

        Query query = manager.createNativeQuery(sql);

        return (List<UserAccount>) query.getResultList().stream()
                .map(this::mapToUserAccount).collect(Collectors.toList());
    }

    /**
     * Get user account by id
     *
     * @return user account
     * @throws Exception when id not found
     */
    public UserAccount getUserAccountById(int id) throws Exception {
        if (id == 0) {
            throw new NotFoundException("Id is required");
        }

        String sql = "" +
                " SELECT DISTINCT *" +
                " FROM USERACCOUNT" +
                " WHERE ID = :id" +
                "     AND DELETED = 0" +
                " ORDER BY ID DESC";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("id", id);

        return (UserAccount) query.getResultList().stream()
                .map(this::mapToUserAccount).collect(Collectors.toList());
    }

    /**
     * Delete user account by id
     *
     * @param id - user account id
     * @return string message
     * @throws Exception when id not found or user acoount not found
     */
    public String deleteUserAccountById(int id) throws Exception {
        if (id == 0) {
            throw new NotFoundException("Id is required");
        }

        UserAccount userAccount = getUserAccountById(id);

        if (userAccount == null) {
            throw new NotFoundException("User account not found with the id " + id);
        }

        String sql = "" +
                " INSERT INTO USERACCOUNT (LAST_MODIFIED_DATETIME, DELETED_DATETIME, DELETED) " +
                " VALUES(GETDATE(), GETDATE(), 1)" +
                " WHERE ID = :id" +
                "     AND DELETED = 0" +
                " ORDER BY ID DESC";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("id", id);

        query.getResultList();

        return String.format("User %s %s with id %d deleted successfully.", userAccount.getFirstName(), userAccount.getLastName(), id);
    }

    /**
     * Map object to User Account Object
     *
     * @param obj - Object
     * @return User Account
     */
    private UserAccount mapToUserAccount(Object obj) {
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