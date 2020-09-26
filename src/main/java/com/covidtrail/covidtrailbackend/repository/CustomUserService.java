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

	public CustomUser findByUserName(String userName) {

		StringBuilder  sql = new StringBuilder();
		sql.append("SELECT ID, USERNAME, PASSWORD, ROLE, FIRSTNAME, LASTNAME, DELETED \n");
		sql.append("	FROM USERACCOUNT \n");
		sql.append("	WHERE USERNAME = :userName");

		Query query = manager.createNativeQuery(sql.toString());
        query.setParameter("userName", userName);
		
        Object[] val = (Object[]) query.getSingleResult();
        
		return new CustomUser(new CustomUserBuilder()
				.id((Integer) val[0])
				.username((String) val[1])
				.password((String) val[2])
				.roles((String) val[3])
				.firstName((String) val[4])
				.lastName((String) val[5])
				.deleted((Integer) val[6]));
	}

}
