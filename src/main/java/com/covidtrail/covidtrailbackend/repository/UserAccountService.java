package com.covidtrail.covidtrailbackend.repository;

import com.covidtrail.covidtrailbackend.dto.UserAccountDto;
import com.covidtrail.covidtrailbackend.model.UserAccount;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAccountService {
    @Autowired
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
    public UserAccountDto getUserAccountById(int id) throws Exception {
        if (id == 0) {
            throw new NotFoundException("Id is required");
        }

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

//        return (UserAccountDto) query.getResultList().stream()
//                .map(x -> {
//                    try {
//                        return mapToUserAccountDto(x);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    return x;
//                }).collect(Collectors.toList());
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
                " INSERT INTO USERACCOUNT (LAST_MODIFIED_DATETIME, DELETED_DATETIME, DELETED) " +
                " VALUES(GETDATE(), GETDATE(), 1)" +
                " WHERE ID = :id" +
                "     AND DELETED = 0" +
                " ORDER BY ID DESC";

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