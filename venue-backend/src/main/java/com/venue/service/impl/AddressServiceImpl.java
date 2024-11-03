package com.venue.service.impl;

import com.venue.dto.AddressDto;
import com.venue.entity.Address;
import com.venue.mapper.AddressMapper;
import com.venue.repository.AddressRepository;
// import com.venue.repository.VenueRepository;
import com.venue.service.AddressService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    private AddressRepository addressRepository;
    // private VenueRepository venueRepository;

    @Override
    public AddressDto createAddress(AddressDto addressDto) {
        logger.info("Creating a new address: {}", addressDto);

        // Create an Address using the mapper
        Address address = AddressMapper.mapToAddress(addressDto);

        logger.info("Saving the new address to the database");
        // Save the address using the repository
        Address savedAddress = addressRepository.save(address);

        logger.info("Address saved successfully with ID: {}", savedAddress.getAddressId());
        // Convert the saved Address back to AddressDto
        return AddressMapper.mapToAddressDto(savedAddress);
    }

    @Override
    public AddressDto getAddressById(Integer addressId) {
        logger.info("Attempting to find address with ID: {}", addressId);

        Address address = addressRepository.findById(Long.valueOf(addressId))
                .orElseThrow(() -> {
                    logger.error("Address with ID: {} not found", addressId);
                    return new RuntimeException("Address not found");
                });

        logger.info("Address with ID: {} found", addressId);
        return AddressMapper.mapToAddressDto(address); // Ensure this is correctly converting
    }

    @Override
    public List<AddressDto> getAllAddress() {
        List<Address> addresses = addressRepository.findAll();

        return addresses.stream().map(AddressMapper::mapToAddressDto)
                .collect(Collectors.toList());
    }

    // PUT method: Update a full address
    @Override
    public AddressDto updateAddress(Integer addressId, AddressDto addressDto) {
        logger.info("Updating address with ID: {}", addressId);

        // Fetch the address from the repository
        Address address = addressRepository.findById(Long.valueOf(addressId))
                .orElseThrow(() -> {
                    logger.error("Address with ID: {} not found", addressId);
                    return new RuntimeException("Address not found");
                });

        // Update the address with the new details
        address.setBuildingName(addressDto.getBuildingName());
        address.setRoomArea(addressDto.getRoomArea());
        address.setCampusName(addressDto.getCampusName());
        address.setLandmark(addressDto.getLandmark());

        logger.info("Saving updated address to the database");
        // Save the updated address to the repository
        Address updatedAddress = addressRepository.save(address);

        logger.info("Address with ID: {} updated successfully", updatedAddress.getAddressId());

        // Return the updated AddressDto
        return AddressMapper.mapToAddressDto(updatedAddress);
    }

    // PATCH method: Partially update an address
    @Override
    public AddressDto patchAddress(Integer addressId, AddressDto addressDto) {
        logger.info("Patching address with ID: {}", addressId);

        // Fetch the address from the repository
        Address address = addressRepository.findById(Long.valueOf(addressId))
                .orElseThrow(() -> {
                    logger.error("Address with ID: {} not found", addressId);
                    return new RuntimeException("Address not found");
                });

        // Update only the non-null fields from the patch request
        if (addressDto.getBuildingName() != null) {
            address.setBuildingName(addressDto.getBuildingName());
        }
        if (addressDto.getRoomArea() != null) {
            address.setRoomArea(addressDto.getRoomArea());
        }
        if (addressDto.getCampusName() != null) {
            address.setCampusName(addressDto.getCampusName());
        }
        if (addressDto.getLandmark() != null) {
            address.setLandmark(addressDto.getLandmark());
        }

        logger.info("Saving partially updated address to the database");
        // Save the partially updated address
        Address patchedAddress = addressRepository.save(address);

        logger.info("Address with ID: {} patched successfully", patchedAddress.getAddressId());

        // Return the patched AddressDto
        return AddressMapper.mapToAddressDto(patchedAddress);
    }

    // DELETE method: Delete an address by its ID
    @Override
    public void deleteAddress(Integer addressId) {
        logger.info("Attempting to delete address with ID: {}", addressId);

        // Check if the address exists
        Address address = addressRepository.findById(Long.valueOf(addressId))
                .orElseThrow(() -> {
                    logger.error("Address with ID: {} not found", addressId);
                    return new RuntimeException("Address not found");
                });

        logger.info("Deleting address with ID: {}", addressId);
        // Delete the address from the repository
        addressRepository.delete(address);

        logger.info("Address with ID: {} deleted successfully", addressId);
    }
}
