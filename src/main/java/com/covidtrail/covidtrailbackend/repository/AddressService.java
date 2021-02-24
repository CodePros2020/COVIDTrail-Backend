package com.covidtrail.covidtrailbackend.repository;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.covidtrail.covidtrailbackend.dto.AddressDto;
import com.covidtrail.covidtrailbackend.dto.AddressUpdateDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    protected EntityManager manager;

    /**
     * Get address by id
     *
     * @param - address id
     * @return Account Details
     */
    public AddressDto getAddressById(int id) {
                "ID," +
                "     ADDRESS_LINE_ONE," +
                "     ADDRESS_LINE_TWO," +
                "     CITY," +
                "     PROVINCE," +
                "     POSTAL_CODE" +
                " WHERE ID = :id" +
                "     AND DELETED = 0" +
                " ORDER BY ID DESC";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("id", id);

        return mapToAddressDto(query.getSingleResult());
    }

    @Transactional
    public String updateAddressById(int id, AddressUpdateDto dto, boolean isBusiness) {
        String sql = "" + "UPDATE ADDRESS " + "SET    ADDRESS_LINE_ONE = :addressLineOne, "
                + "       ADDRESS_LINE_TWO = :addressLineTwo, " + "       CITY = :city, "
                + "       PROVINCE = :province, " + "       POSTAL_CODE = :postalCode, "
                + "       LAST_MODIFIED_DATETIME = GETDATE() " + "WHERE id in (select b.ADDRESS_ID from"
                + (isBusiness ? " BUSINESSACCOUNT" : " USERACCOUNT") + " b where b.id = :id)";

        Query query = manager.createNativeQuery(sql);
        query.setParameter("addressLineOne", dto.getAddressLineOne());
        query.setParameter("addressLineTwo", dto.getAddressLineTwo());
        query.setParameter("city", dto.getCity());
        query.setParameter("province", dto.getProvince());
        query.setParameter("postalCode", dto.getPostalCode());
        query.setParameter("id", id);

        query.executeUpdate();

        return "Address updated successfully.";
    }

    /**
     * Map object to AddressDto Object
     *
     * @param obj - Object
     * @return AddressDto
     */
    public AddressDto mapToAddressDto(Object obj) {
        Object[] val = (Object[]) obj;
        AddressDto addressDto = new AddressDto();

        addressDto.setId(Integer.parseInt(val[0].toString()));
        addressDto.setAddressLineOne((String) val[1]);
        addressDto.setAddressLineTwo((String) val[2]);
        addressDto.setCity((String) val[3]);
        addressDto.setProvince((String) val[4]);
        addressDto.setPostalCode((String) val[5]);

        return addressDto;
    }
}
