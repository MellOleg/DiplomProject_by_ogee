package org.olegmell.service;

import org.olegmell.domain.Address;
import org.olegmell.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService (AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses () {
        return addressRepository.findAllAddress();
    }

    public List<Address> searchAddress (String keyword) {
        if (keyword != null) {
            return addressRepository.search(keyword);
        }
        return addressRepository.findAllAddress();
    }

}
