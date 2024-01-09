package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ParkingAppDTO implements Serializable {

    private String nom;
    private String disponibilite;

    public ParkingAppDTO(String nom, String disponibilite) {
        this.nom = nom;
        this.disponibilite = disponibilite;
    }

}
