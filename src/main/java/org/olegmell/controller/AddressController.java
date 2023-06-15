package org.olegmell.controller;

import org.olegmell.domain.Address;
import org.olegmell.domain.AddressItem;
import org.olegmell.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}