package com.venue.controller;

import com.venue.dto.AddressDto;
import com.venue.service.AddressService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/address")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
    private AddressService addressService;

    // Create Address (POST)
    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
        logger.info("Creating a new address: {}", addressDto);
        AddressDto savedAddress = addressService.createAddress(addressDto);
        logger.info("Successfully created a new address with ID: {}", savedAddress.getAddressId());
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    // Get Address by ID (GET)
    @GetMapping("{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable("id") Integer addressId) {
        logger.info("Received request to retrieve address with ID: {}", addressId);
        AddressDto addressDto = addressService.getAddressById(addressId);
        if (addressDto != null) {
            logger.info("Successfully retrieved address with ID: {}", addressId);
        } else {
            logger.warn("No address found with ID: {}", addressId);
        }
        return ResponseEntity.ok(addressDto);
    }
    // Get all Addresses (GET)
    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddress() {
        logger.info("Received request to retrieve all addresses");
        List<AddressDto> addresses = addressService.getAllAddress();
        if (addresses.isEmpty()) {
            logger.warn("No addresses found");
        } else {
            logger.info("Successfully retrieved {} addresses", addresses.size());
        }
        return ResponseEntity.ok(addresses);
    }

    // Update Address (PUT) - Full update
    @PutMapping("{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable("id") Integer addressId, @RequestBody AddressDto addressDto) {
        logger.info("Updating address with ID: {}", addressId);
        AddressDto updatedAddress = addressService.updateAddress(addressId, addressDto);
        return ResponseEntity.ok(updatedAddress);
    }

    // Partially Update Address (PATCH)
    @PatchMapping("{id}")
    public ResponseEntity<AddressDto> patchAddress(@PathVariable("id") Integer addressId, @RequestBody AddressDto addressDto) {
        logger.info("Patching address with ID: {}", addressId);
        AddressDto patchedAddress = addressService.patchAddress(addressId, addressDto);
        return ResponseEntity.ok(patchedAddress);
    }

    // Delete Address (DELETE)
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") Integer addressId) {
        logger.info("Deleting address with ID: {}", addressId);
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
