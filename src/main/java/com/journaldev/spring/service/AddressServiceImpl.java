package com.journaldev.spring.service;

import com.journaldev.spring.dao.AddressDAO;
import com.journaldev.spring.dao.PersonDAOImpl;
import com.journaldev.spring.model.Address;
import com.journaldev.spring.model.Person;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Saqo on 15.11.2016.
 */
public class AddressServiceImpl implements  AddressService {

    private AddressDAO addressDAO;

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Override
    @Transactional
    public void addAddress(Address address) {
        this.addressDAO.addAddress(address);
    }


}
