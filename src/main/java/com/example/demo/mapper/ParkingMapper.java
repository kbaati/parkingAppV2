package com.example.demo.mapper;

import com.example.demo.dto.ParkingDTO;
import com.example.demo.model.Parking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Kbaati
 * le mapper model dto
 */
@Mapper(componentModel = "spring")
public interface ParkingMapper {
    @Mapping(source = "parkingId", target = "parkingId")
    ParkingDTO parkingToParkingDTO(Parking parking);

    @Mapping(source = "parkingId", target = "parkingId")
    Parking parkingDTOToParking(ParkingDTO parkingDTO);
}
