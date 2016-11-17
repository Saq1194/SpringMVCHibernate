package com.journaldev.spring.service;

import com.journaldev.spring.dao.AddressDAO;
import com.journaldev.spring.dao.PersonDAOImpl;
import com.journaldev.spring.model.Address;
import com.journaldev.spring.model.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    @Transactional
    public void updateAddress(Address address) {
        this.addressDAO.updateAddress(address);
    }

    @Override
    @Transactional
    public Address getAddressById(int id) {
        return this.addressDAO.getAddressById(id);
    }

    @Override
    @Transactional
    public void removeAddress(int id) {
        this.addressDAO.removeAddress(id);
    }

    @Override
    public List<Address> listAddress() {
        return this.addressDAO.listAddress();
    }


}
