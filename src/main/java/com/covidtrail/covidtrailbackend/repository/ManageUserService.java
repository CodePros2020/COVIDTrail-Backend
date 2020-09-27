package com.covidtrail.covidtrailbackend.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ManageUserService {
    @Autowired
    protected EntityManager manager;

    /**
     * Update password
     *
     * @param password   - password to update
     * @param isBusiness - check if it's business account
     * @param phone      - phone number to target
     * @return string message
     */
    @Transactional
    public String updatePassword(String password, boolean isBusiness, String phone) {
        String business = "BUSINESSACCOUNT";
        String userAccount = "USERACCOUNT";

        String sql = "" +
                " UPDATE "
                + (isBusiness ? business : userAccount) +
                " SET PASSWORD = :password" +
                " WHERE PHONE = :phone" +
                "     AND DELETED = 0";

        Query query = manager.createNativeQuery(sql);
        query.setParameter("password", new BCryptPasswordEncoder().encode(password));
        query.setParameter("phone", phone);

        query.executeUpdate();

        return "Password updated successfully";
    }
}
