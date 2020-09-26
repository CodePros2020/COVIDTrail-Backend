package com.covidtrail.covidtrailbackend.repository;

import com.covidtrail.covidtrailbackend.dto.UserAccountDto;
import com.covidtrail.covidtrailbackend.dto.UserAccountNameUpdateDto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAccountService {
    @Autowired
    @PersistenceContext
    protected EntityManager manager;

    @Autowired
    protected AddressService addressService;

    /**
     * Get the list of all user accounts
     *
     * @return list of user accounts
     */
    public List<UserAccountDto> getAllUserAccounts() {
        String sql = "" +
                " SELECT DISTINCT" +
                "     ID," +
                "     FIRSTNAME," +
                "     MIDDLENAME," +
                "     LASTNAME," +
                "     EMAIL," +
                "     PHONE," +
                "     ADDRESS_ID" +
                " FROM USERACCOUNT" +
                " WHERE DELETED = 0" +
                " ORDER BY ID DESC";

        Query query = manager.createNativeQuery(sql);

        return (List<UserAccountDto>) query.getResultList().stream()
                .map(this::mapToUserAccountDto).collect(Collectors.toList());
    }

    /**
     * Get user account by id
     *
     * @return user account
     * @throws Exception when id not found
     */
    public UserAccountDto getUserAccountById(int id) {
        String sql = "" +
                " SELECT DISTINCT " +
                "     ID," +
                "     FIRSTNAME," +
                "     MIDDLENAME," +
                "     LASTNAME," +
                "     EMAIL," +
                "     PHONE," +
                "     ADDRESS_ID" +
                " FROM USERACCOUNT" +
                " WHERE ID = :id" +
                "     AND DELETED = 0" +
                " ORDER BY ID DESC";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("id", id);

        return mapToUserAccountDto(query.getSingleResult());
    }

    /**
     * Update user's names by id
     *
     * @param id      - user account id
     * @param request - UserAccountNameUpdateDto
     * @return string message
     * @throws Exception when id not found or user account not found
     */
    @Transactional
    public String updateUserNamesById(int id, UserAccountNameUpdateDto request) throws Exception {
        if (id == 0) {
            throw new NotFoundException("Id is required");
        }

        UserAccountDto userAccount = getUserAccountById(id);

        if (userAccount == null) {
            throw new NotFoundException("User account not found with the id " + id);
        }

        String sql = "" +
                " UPDATE USERACCOUNT" +
                " SET LAST_MODIFIED_DATETIME = GETDATE()," +
                "     FIRSTNAME = :fName," +
                "     MIDDLENAME = :mName," +
                "     LASTNAME = :lName" +
                " WHERE ID = :id" +
                "     AND DELETED = 0";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("fName", request.getFirstName());
        query.setParameter("mName", request.getMiddleName());
        query.setParameter("lName", request.getLastName());
        query.setParameter("id", id);

        query.executeUpdate();

        return String.format("User %d's name has been updated to '%s, %s %s'.", id,
                request.getLastName(), request.getFirstName(), request.getMiddleName());
    }

    /**
     * Update user's email address by id
     *
     * @param id      - user account id
     * @param newEmail - new email address
     * @return string message
     * @throws Exception when id not found or user account not found
     */
    @Transactional
    public String updateUserEmailById(int id, String newEmail) throws Exception {
        if (id == 0) {
            throw new NotFoundException("Id is required");
        }

        UserAccountDto userAccount = getUserAccountById(id);

        if (userAccount == null) {
            throw new NotFoundException("User account not found with the id " + id);
        }

        String sql = "" +
                " UPDATE USERACCOUNT" +
                " SET LAST_MODIFIED_DATETIME = GETDATE()," +
                "     EMAIL = :newEmail" +
                " WHERE ID = :id" +
                "     AND DELETED = 0";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("newEmail", newEmail);
        query.setParameter("id", id);

        query.executeUpdate();

        return String.format("User %d's email address has been updated to %s.", id, newEmail);
    }

    /**
     * Update user's phone number by id
     *
     * @param id      - user account id
     * @param newPhone - new phone number
     * @return string message
     * @throws Exception when id not found or user account not found
     */
    @Transactional
    public String updateUserPhoneById(int id, String newPhone) throws Exception {
        if (id == 0) {
            throw new NotFoundException("Id is required");
        }

        UserAccountDto userAccount = getUserAccountById(id);

        if (userAccount == null) {
            throw new NotFoundException("User account not found with the id " + id);
        }

        String sql = "" +
                " UPDATE USERACCOUNT" +
                " SET LAST_MODIFIED_DATETIME = GETDATE()," +
                "     PHONE = :newPhone" +
                " WHERE ID = :id" +
                "     AND DELETED = 0";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("newPhone", newPhone);
        query.setParameter("id", id);

        query.executeUpdate();

        return String.format("User %d's phone number has been updated to %s.", id, newPhone);
    }

    /**
     * Delete user account by id
     *
     * @param id - user account id
     * @return string message
     * @throws Exception when id not found or user account not found
     */
    @Transactional
    public String deleteUserAccountById(int id) throws Exception {
        if (id == 0) {
            throw new NotFoundException("Id is required");
        }

        UserAccountDto userAccount = getUserAccountById(id);

        if (userAccount == null) {
            throw new NotFoundException("User account not found with the id " + id);
        }

        String sql = "" +
                " UPDATE USERACCOUNT" +
                " SET LAST_MODIFIED_DATETIME = GETDATE()," +
                "     DELETED_DATETIME = GETDATE()," +
                "     DELETED = 1" +
                " WHERE ID = :id" +
                "     AND DELETED = 0";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("id", id);

        query.executeUpdate();

        return String.format("User %s %s with id %d deleted successfully.", userAccount.getFirstName(), userAccount.getLastName(), id);
    }

    /**
     * Map object to User Account Object
     *
     * @param obj - Object
     * @return User Account
     */
    private UserAccountDto mapToUserAccountDto(Object obj) {
        Object[] val = (Object[]) obj;
        UserAccountDto userAccount = new UserAccountDto();

        userAccount.setId(Integer.parseInt(val[0].toString()));
        userAccount.setFirstName((String) val[1]);
        userAccount.setMiddleName((String) val[2]);
        userAccount.setLastName((String) val[3]);
        userAccount.setEmail((String) val[4]);
        userAccount.setPhone((String) val[5]);
        userAccount.setAddress(addressService.getAddressById(Integer.parseInt(String.valueOf(val[6]))));

        return userAccount;
    }
}