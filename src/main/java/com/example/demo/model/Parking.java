package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Kbaati
 *
 * l'entity parking
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "PARKING")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "parking_id")
    private Long parkingId;

    @Column(name = "commune")
    private String commune;

    @Column(name = "url_list_parking")
    private String urlListParking;

    @Column(name = "url_dispo_parking")
    private String urlDispoParking;

    @Column(name = "url_list_parking_select1")
    private String urlListParkingSelect1;

    @Column(name = "url_list_parking_select2")
    private String urlListParkingSelect2;

    @Column(name = "url_list_parking_select3")
    private String urlListParkingSelect3;

    @Column(name = "url_dispo_parking_select1")
    private String urlDispoParkingSelect1;

    @Column(name = "url_dispo_parking_select2")
    private String urlDispoParkingSelect2;

    @Column(name = "url_dispo_parking_select3")
    private String urlDispoParkingSelect3;
}
