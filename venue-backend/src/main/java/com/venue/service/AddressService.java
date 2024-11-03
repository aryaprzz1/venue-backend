package com.venue.service;

import com.venue.dto.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto createAddress(AddressDto addressDto);

    AddressDto getAddressById(Integer addressId);

    List<AddressDto> getAllAddress();

    // PUT method: Update a full address
    AddressDto updateAddress(Integer addressId, AddressDto addressDto);

    // PATCH method: Partially update an address
    AddressDto patchAddress(Integer addressId, AddressDto addressDto);

    // DELETE method: Delete an address by its ID
    void deleteAddress(Integer addressId);
}
