package com.journaldev.spring.dao;

import com.journaldev.spring.model.Address;
import com.journaldev.spring.model.Person;

import java.util.List;

/**
 * Created by Saqo on 15.11.2016.
 */
public interface AddressDAO {
    public void addAddress(Address address);
    public void updateAddress(Address address);
    public Address getAddressById(int id);
    public void removeAddress(int id);

    public List<Address> listAddress();
}
