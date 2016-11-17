package com.journaldev.spring.service;

import com.journaldev.spring.model.Address;
import com.journaldev.spring.model.Person;

import java.util.List;

/**
 * Created by Saqo on 15.11.2016.
 */
public interface AddressService {

    void addAddress(Address address);
    void updateAddress(Address address);
    Address getAddressById(int id);
    void removeAddress(int id);

    public List<Address> listAddress();



}
