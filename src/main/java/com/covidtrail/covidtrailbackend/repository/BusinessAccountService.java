package com.covidtrail.covidtrailbackend.repository;

import com.covidtrail.covidtrailbackend.dto.BusinessAccountDto;
import com.covidtrail.covidtrailbackend.dto.UserAccountDto;
import com.covidtrail.covidtrailbackend.dto.UserAccountNameUpdateDto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessAccountService {
    @Autowired
    protected EntityManager manager;

    @Autowired
    protected AddressService addressService;

    /**
     * Get the list of all business accounts
     *
     * @return list of business account
     */
    public List<BusinessAccountDto> getAllBusinessAccounts() {
        String sql = "" +
                " SELECT DISTINCT" +
                "     ID," +
                "     BUSINESSNAME," +
                "     EMAIL," +
                "     PHONE," +
                "     ADDRESS_ID" +
                " FROM BUSINESSACCOUNT" +
                " WHERE DELETED = 0" +
                " ORDER BY ID DESC";

        Query query = manager.createNativeQuery(sql);

        return (List<BusinessAccountDto>) query.getResultList().stream()
                .map(this::mapToBusinessAccount).collect(Collectors.toList());
    }

    /**
     * Get business account by id
     *
     * @return business account
     * @throws Exception when id not found
     */
    public BusinessAccountDto getBusinessAccountById(int id) throws Exception {
        if (id == 0) {
            throw new NotFoundException("Id is required");
        }

        String sql = "" +
                " SELECT DISTINCT" +
                "     ID," +
                "     BUSINESSNAME," +
                "     EMAIL," +
                "     PHONE," +
                "     ADDRESS_ID" +
                " FROM BUSINESSACCOUNT" +
                " WHERE ID = :id" +
                "     AND DELETED = 0" +
                " ORDER BY ID DESC";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("id", id);

        return (BusinessAccountDto) query.getResultList().stream()
                .map(this::mapToBusinessAccount).collect(Collectors.toList());
    }

    /**
     * Update business names by id
     *
     * @param id      - business account id
     * @param newName - new business name
     * @return string message
     * @throws Exception when id not found or business account not found
     */
    @Transactional
    public String updateBusinessNamesById(int id, String newName) throws Exception {
        if (id == 0) {
            throw new NotFoundException("Id is required");
        }

        BusinessAccountDto businessAccountDto = getBusinessAccountById(id);

        if (businessAccountDto == null) {
            throw new NotFoundException("Business account not found with the id " + id);
        }

        String sql = "" +
                " UPDATE BUSINESSACCOUNT" +
                " SET LAST_MODIFIED_DATETIME = GETDATE()," +
                "     BUSINESSNAME = :name" +
                " WHERE ID = :id" +
                "     AND DELETED = 0";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("name", newName);
        query.setParameter("id", id);

        query.executeUpdate();

        return String.format("Business %d's name has been updated to %s.", id, newName);
    }

    /**
     * Update business email address by id
     *
     * @param id      - business account id
     * @param newEmail - new email address
     * @return string message
     * @throws Exception when id not found or business account not found
     */
    @Transactional
    public String updateBusinessEmailById(int id, String newEmail) throws Exception {
        if (id == 0) {
            throw new NotFoundException("Id is required");
        }

        BusinessAccountDto businessAccountDto = getBusinessAccountById(id);

        if (businessAccountDto == null) {
            throw new NotFoundException("Business account not found with the id " + id);
        }

        String sql = "" +
                " UPDATE BUSINESSACCOUNT" +
                " SET LAST_MODIFIED_DATETIME = GETDATE()," +
                "     EMAIL = :newEmail" +
                " WHERE ID = :id" +
                "     AND DELETED = 0";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("newEmail", newEmail);
        query.setParameter("id", id);

        query.executeUpdate();

        return String.format("Business %d's email address has been updated to %s.", id, newEmail);
    }

    /**
     * Update business' phone number by id
     *
     * @param id      - business account id
     * @param newPhone - new phone number
     * @return string message
     * @throws Exception when id not found or business account not found
     */
    @Transactional
    public String updateBusinessPhoneById(int id, String newPhone) throws Exception {
        if (id == 0) {
            throw new NotFoundException("Id is required");
        }

        BusinessAccountDto businessAccountDto = getBusinessAccountById(id);

        if (businessAccountDto == null) {
            throw new NotFoundException("Business account not found with the id " + id);
        }

        String sql = "" +
                " UPDATE BUSINESSACCOUNT" +
                " SET LAST_MODIFIED_DATETIME = GETDATE()," +
                "     PHONE = :newPhone" +
                " WHERE ID = :id" +
                "     AND DELETED = 0";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("newPhone", newPhone);
        query.setParameter("id", id);

        query.executeUpdate();

        return String.format("Business %d's phone number has been updated to %s.", id, newPhone);
    }

    /**
     * Delete business account by id
     *
     * @param id - business account id
     * @return string message
     * @throws Exception when id not found or business account not found
     */
    @Transactional
    public String deleteBusinessAccountById(int id) throws Exception {
        if (id == 0) {
            throw new NotFoundException("Id is required");
        }

        BusinessAccountDto businessAccount = getBusinessAccountById(id);

        if (businessAccount == null) {
            throw new NotFoundException("Business account not found with the id " + id);
        }

        String sql = "" +
                " UPDATE BUSINESSACCOUNT" +
                " SET LAST_MODIFIED_DATETIME = GETDATE(), DELETED_DATETIME = GETDATE(), DELETED = 1" +
                " WHERE ID = :id AND DELETED = 0";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("id", id);

        query.executeUpdate();

        return String.format("Business %s with id %d deleted successfully.", businessAccount.getBusinessName(), id);
    }

    /**
     * Map object to Business Account Object
     *
     * @param obj - Object
     * @return Business Account
     */
    private BusinessAccountDto mapToBusinessAccount(Object obj) {
        Object[] val = (Object[]) obj;
        BusinessAccountDto businessAccountDto = new BusinessAccountDto();

        businessAccountDto.setId(Integer.parseInt(val[0].toString()));
        businessAccountDto.setBusinessName((String) val[1]);
        businessAccountDto.setEmail((String) val[2]);
        businessAccountDto.setPhone((String) val[3]);
        businessAccountDto.setAddress(addressService.getAddressById(Integer.parseInt(val[4].toString())));

        return businessAccountDto;
    }
}
