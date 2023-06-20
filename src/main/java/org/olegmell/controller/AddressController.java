package org.olegmell.controller;

import org.olegmell.domain.AddressItem;
import org.olegmell.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<AddressItem> addressList(@RequestParam(value = "q", required = false) String query) {

        return addressService.searchAddress(query)
                .stream()
                .limit(15)
                .collect(Collectors.toList());
    }

    @GetMapping("/{addressId}")
    public AddressItem getAddress(@PathVariable Integer addressId){
        return addressService.getAddressItem(addressId);
    }
}