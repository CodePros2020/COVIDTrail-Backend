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
		sql.append("SELECT U.ID, U.PHONE, U.PASSWORD, U.FIRSTNAME, U.LASTNAME, U.EMAIL, "
				+ "A.ADDRESS_LINE_ONE, A.ADDRESS_LINE_TWO, A.PROVINCE, A.POSTAL_CODE, A.CITY, U.MIDDLENAME, U.TOKEN \n");
		sql.append("	FROM USERACCOUNT U JOIN ADDRESS A ON A.ID = U.ADDRESS_ID \n");
		sql.append("	WHERE U.PHONE = :userName");
		sql.append("	AND U.DELETED = 0");

		Query query = manager.createNativeQuery(sql.toString());
        query.setParameter("userName", username);
		
        try {
        	Object[] val = (Object[]) query.getSingleResult();
        	
        	return new CustomUser(new CustomUserBuilder()
        			.id((Integer) val[0])
        			.phone((String) val[1])
        			.password((String) val[2])
        			.firstName((String) val[3])
        			.lastName((String) val[4])
		        	.email((String) val[5])
		        	.addressLineOne((String) val[6])
		        	.addressLineTwo((String) val[7])
		        	.province((String) val[8])
		        	.postalCode((String) val[9])
		        	.city((String) val[10])
		        	.middleName((String) val[11])
        			.token((String) val[12]));
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
		sql.append("SELECT B.ID, B.PHONE, B.PASSWORD, B.BUSINESSNAME, B.EMAIL, ");
		sql.append("A.ADDRESS_LINE_ONE, A.ADDRESS_LINE_TWO, A.PROVINCE, A.POSTAL_CODE, A.CITY, B.TOKEN \n");
		sql.append("	FROM BUSINESSACCOUNT B JOIN ADDRESS A ON A.ID = B.ADDRESS_ID \n");
		sql.append("	WHERE B.PHONE = :userName");
		sql.append("	AND B.DELETED = 0");
		
		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("userName", username);
		
		try {
			Object[] val = (Object[]) query.getSingleResult();
			
			return new CustomUser(new CustomUserBuilder()
					.id((Integer) val[0])
					.phone((String) val[1])
					.password((String) val[2])
					.businessName((String) val[3])
					.email((String) val[4])
		        	.addressLineOne((String) val[5])
		        	.addressLineTwo((String) val[6])
		        	.province((String) val[7])
		        	.postalCode((String) val[8])
		        	.city((String) val[9])
		        	.token((String) val[10]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
