package com.example.demo.service.impl;

import com.example.demo.dto.ParkingAppDTO;
import com.example.demo.dto.ParkingDTO;
import com.example.demo.mapper.ParkingMapper;
import com.example.demo.model.ApiResponse;
import com.example.demo.model.DisponibilityParking;
import com.example.demo.model.ListParking;
import com.example.demo.model.Parking;
import com.example.demo.repository.ParkingRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Kbaati
 * implementation de notre service
 */

@Service
public class ParkingServiceImpl implements com.example.demo.service.ParkingService {

    private final ParkingRepository parkingRepository;
    private final ParkingMapper parkingMapper;


    public ParkingServiceImpl(ParkingRepository parkingRepository, ParkingMapper parkingMapper) {
        this.parkingRepository = parkingRepository;
        this.parkingMapper = parkingMapper;
    }

    @Override
    public ParkingDTO getParkingByCommune(String commune) {
        Parking parking = parkingRepository.findByCommune(commune);
        return parkingMapper.parkingToParkingDTO(parking);
    }

    @Override
    public List<ListParking> getListParking(ParkingDTO parkingDTO) {
        return fetchData(
                parkingDTO.getUrlListParking(),
                parkingDTO.getUrlListParking(),
                new String[]{parkingDTO.getUrlListParkingSelect1(), parkingDTO.getUrlListParkingSelect2(), parkingDTO.getUrlListParkingSelect3()},
                new ParameterizedTypeReference<ApiResponse<ListParking>>() {
                }
        );
    }

    @Override
    public List<DisponibilityParking> getDispoParking(ParkingDTO parkingDTO) {
        return fetchData(
                parkingDTO.getUrlDispoParking(),
                parkingDTO.getUrlDispoParking(),
                new String[]{parkingDTO.getUrlDispoParkingSelect1(), parkingDTO.getUrlDispoParkingSelect2(), parkingDTO.getUrlDispoParkingSelect3()},
                new ParameterizedTypeReference<ApiResponse<DisponibilityParking>>() {
                }
        );
    }

    private List<ParkingAppDTO> createParkingDTOList(List<ListParking> parkings, List<DisponibilityParking> disponibilityParkings, ParkingDTO parkingDTO, int startIndex, int endIndex) {
        List<ParkingAppDTO> parkingDtos = new ArrayList<>();

        for (ListParking parking : parkings.subList(startIndex, endIndex)) {
            String parkingName = (String) parking.getProperties().get(parkingDTO.getUrlListParkingSelect1());

            Optional<DisponibilityParking> matchingDisponibility = disponibilityParkings.stream()
                    .filter(disponibilityParking -> parkingName.equals(disponibilityParking.getProperties().get(parkingDTO.getUrlDispoParkingSelect1())))
                    .findFirst();

            if (matchingDisponibility.isPresent()) {
                DisponibilityParking disponibility = matchingDisponibility.get();
                ParkingAppDTO parkingDto = new ParkingAppDTO(parkingName, (String) disponibility.getProperties().get(parkingDTO.getUrlDispoParkingSelect2()));
                parkingDtos.add(parkingDto);
            } else {
                // Handle case where no matching DisponibilityParking is found for the ListParking
                ParkingAppDTO parkingDto = new ParkingAppDTO(parkingName, "N/A");
                parkingDtos.add(parkingDto);
            }
        }

        return parkingDtos;
    }

    @Override
    public List<ParkingAppDTO> getParkingWithDisponibility(ParkingDTO parkingDTO) {
        List<ListParking> parkings = getListParking(parkingDTO);
        List<DisponibilityParking> disponibilityParkings = getDispoParking(parkingDTO);

        return createParkingDTOList(parkings, disponibilityParkings, parkingDTO, 0, parkings.size());
    }

    @Override
    public List<ParkingAppDTO> getParkingWithDisponibility(ParkingDTO parkingDTO, int page, int size) {
        List<ListParking> parkings = getListParking(parkingDTO);
        List<DisponibilityParking> disponibilityParkings = getDispoParking(parkingDTO);

        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, parkings.size());

        return createParkingDTOList(parkings, disponibilityParkings, parkingDTO, startIndex, endIndex);
    }



}
