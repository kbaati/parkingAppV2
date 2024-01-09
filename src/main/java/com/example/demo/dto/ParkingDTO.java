package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParkingDTO {

    private Long parkingId;

    private String commune;

    private String urlListParking;

    private String urlDispoParking;

    private String urlListParkingSelect1;

    private String urlListParkingSelect2;

    private String urlListParkingSelect3;

    private String urlDispoParkingSelect1;

    private String urlDispoParkingSelect2;

    private String urlDispoParkingSelect3;
}
