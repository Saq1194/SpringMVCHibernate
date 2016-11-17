package com.journaldev.spring.dao;

import com.journaldev.spring.model.Address;
import com.journaldev.spring.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Saqo on 15.11.2016.
 */
public class AddressDAOImpl implements AddressDAO {

    private SessionFactory sessionFactoryAddress;
    private static final Logger logger = LoggerFactory.getLogger(AddressDAOImpl.class);

    public void setSessionFactoryAddress(SessionFactory sf) {
        this.sessionFactoryAddress = sf;
    }

    @Override
    public void addAddress(Address address) {
        Session session = this.sessionFactoryAddress.getCurrentSession();
        session.persist(address);
        logger.info("Person saved successfully, Person Details="+address);

    }

    @Override
    public void updateAddress(Address address) {
        Session session = this.sessionFactoryAddress.getCurrentSession();
        session.update(address);
        logger.info("Person updated successfully, Person Details="+address);
    }

    @Override
    public Address getAddressById(int id) {
        Session session = this.sessionFactoryAddress.getCurrentSession();
        Address address = (Address) session.load(Address.class, new Integer(id));
        logger.info("Person loaded successfully, Person details="+address);
        return address;
    }

    @Override
    public void removeAddress(int id) {
        Session session = this.sessionFactoryAddress.getCurrentSession();
        Address address= (Address) session.load(Address.class, new Integer(id));
        if(null != address){
            session.delete(address);
        }
        logger.info("Person deleted successfully, person details="+address);
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Address> listAddress() {
        Session session = this.sessionFactoryAddress.getCurrentSession();
        List<Address> addressesList = session.createQuery("from Address").list();
        for(Address address : addressesList){
            logger.info("Person List::"+address);
        }
        return addressesList;
    }
}

