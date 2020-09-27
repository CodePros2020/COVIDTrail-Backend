package com.covidtrail.covidtrailbackend.model;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {

	@Autowired
	protected EntityManager manager;

	public boolean findDuplicatedPhones(String phone) {

		StringBuilder sql = new StringBuilder();
		sql.append("select 1 from USERACCOUNT u \n");
		sql.append("	left join BUSINESSACCOUNT b on b.PHONE = u.PHONE \n");
		sql.append("	left join BUSINESSACCOUNT b2 on b.PHONE = u.PHONE \n");
		sql.append("	left join USERACCOUNT u2 on u2.phone = u.phone \n");
		sql.append("	where u.PHONE = :phone");

		Query query = manager.createNativeQuery(sql.toString());
		query.setParameter("phone", phone);

		try {
			return (Integer) query.getSingleResult() == 1;
		} catch (NoResultException e) {
			return false;
		}
	}

}
