package com.covidtrail.covidtrailbackend.repository;

import com.covidtrail.covidtrailbackend.dto.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
        String sql = "" +
                " SELECT DISTINCT" +
                "     ID," +
                "     ADDRESS_LINE_ONE," +
                "     ADDRESS_LINE_TWO," +
                "     CITY," +
                "     PROVINCE," +
                "     POSTAL_CODE" +
                " FROM ADDRESS" +
                " WHERE ID = :id" +
                "     AND DELETED = 0" +
                " ORDER BY ID DESC";

        Query query = manager.createNativeQuery(sql);

        query.setParameter("id", id);

        return mapToAddressDto(query.getSingleResult());
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
