package com.covidtrail.covidtrailbackend.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidtrail.covidtrailbackend.builders.CustomUserBuilder;
import com.covidtrail.covidtrailbackend.model.CustomUser;

@Service
public class CustomUserService {
	@Autowired
	protected EntityManager manager;

	/**
	 * Find user account by username
	 *
	 * @param username - phone numver of user
	 * @return custom user
	 */
	public CustomUser findUserAccountByUserName(String username) {
		StringBuilder  sql = new StringBuilder();
		sql.append("SELECT ID, PHONE, PASSWORD, FIRSTNAME, LASTNAME \n");
		sql.append("	FROM USERACCOUNT \n");
		sql.append("	WHERE PHONE = :userName");
		sql.append("	AND DELETED = 0");

		Query query = manager.createNativeQuery(sql.toString());
        query.setParameter("userName", username);
		
        try {
        	Object[] val = (Object[]) query.getSingleResult();
        	
        	return new CustomUser(new CustomUserBuilder()
        			.id((Integer) val[0])
        			.phone((String) val[1])
        			.password((String) val[2])
        			.firstName((String) val[3])
        			.lastName((String) val[4]));
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return null;
	}

	/**
	 * Find business account by username
	 *
	 * @param username - phone number
	 * @return custom user
	 */
	public CustomUser findBusinessAccountByUserName(String username) {
		StringBuilder  sql = new StringBuilder();
		sql.append("SELECT ID, PHONE, PASSWORD, BUSINESSNAME \n");
		sql.append("	FROM BUSINESSACCOUNT \n");
		sql.append("	WHERE PHONE = :userName");
		sql.append("	AND DELETED = 0");
		
		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("userName", username);
		
		try {
			Object[] val = (Object[]) query.getSingleResult();
			
			return new CustomUser(new CustomUserBuilder()
					.id((Integer) val[0])
					.phone((String) val[1])
					.password((String) val[2])
					.businessName((String) val[3]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
