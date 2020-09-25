package com.covidtrail.covidtrailbackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class AuthenticationService {
    @Autowired
    protected EntityManager manager;
}
