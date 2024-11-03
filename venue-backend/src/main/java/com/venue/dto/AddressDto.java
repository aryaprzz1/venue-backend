package com.venue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long addressId;      // This should match the JSON input
    private String buildingName;  // Required
    private String roomArea;      // Required
    private String campusName;    // Required
    private String landmark;       // Required
    private Integer venueId;      // Required


}
