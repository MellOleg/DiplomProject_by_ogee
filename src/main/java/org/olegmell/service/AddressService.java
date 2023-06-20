package org.olegmell.service;

import org.olegmell.domain.Address;
import org.olegmell.domain.AddressItem;
import org.olegmell.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService (AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses () {
        return addressRepository.findAllAddress();
    }

    public List<AddressItem> searchAddress (String keyword) {
        if (keyword != null) {
            return addressRepository.search(keyword.toLowerCase())
                    .stream().map(this::mapToAddressItem)
                    .collect(Collectors.toList());
        }
        return addressRepository.findAllAddress()
                .stream().map(this::mapToAddressItem)
                .collect(Collectors.toList());    }

    public AddressItem getAddressItem(Integer addressId){
        return mapToAddressItem(addressRepository.getOne(addressId));

    }

    public Address getAddress(Integer addressId){
        return addressRepository.getOne(addressId);
    }

    private AddressItem mapToAddressItem(Address address) {
        return AddressItem.builder()
                .id(address.getId())
                .text(address.getAddress())
                .build();
    }

}
