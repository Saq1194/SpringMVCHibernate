package com.journaldev.spring.dao;

import com.journaldev.spring.model.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by Saqo on 15.11.2016.
 */
public class AddressDAOImpl implements AddressDAO {

    private SessionFactory sessionFactoryAddress;
    public void setSessionFactoryAddress(SessionFactory sf){
        this.sessionFactoryAddress = sf;
    }

    @Override
    public void addAddress(Address address) {
        Session session = this.sessionFactoryAddress.getCurrentSession();
        session.persist(address);
    }
}
