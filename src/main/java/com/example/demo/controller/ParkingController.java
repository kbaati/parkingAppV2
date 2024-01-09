package com.example.demo.controller;

import com.example.demo.dto.ParkingDTO;
import com.example.demo.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Kbaati
 * notre controlleur..
 */

@RestController
@RequestMapping("/api/v2/parking")
public class ParkingController {
    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }


    // TODO ici je suppose qu'il ya une seule ligne retourner par la BDD, il faut certes modifier le schema de la base de donnee pour que le champ commune soit unique !

//    @GetMapping("/{commune}")
//    public ResponseEntity<ParkingDTO> getParkingByCommune(@PathVariable String commune) {
//        ParkingDTO parkingDTOS = parkingService.getParkingByCommune(commune);
//        return (parkingDTOS != null) ? ResponseEntity.ok(parkingDTOS) : ResponseEntity.notFound().build();
//    }

    // TODO ici je suppose qu'il ya une seule ligne retourner par la BDD, il faut certes modifier le schema de la base de donnee pour que le champ commune soit unique !

    /**
     * Récupérer les informations de stationnement par commune.
     *
     * @param commune La commune pour laquelle les informations de stationnement sont demandées.
     * @return ResponseEntity contenant ParkingDTO si trouvé, ou un statut 404 avec un message d'erreur.
     */
    @GetMapping("/{commune}")
    public ResponseEntity<?> getParkingWithDisponibility(@PathVariable String commune) {
        ParkingDTO parkingDTO = parkingService.getParkingByCommune(commune);

        return Optional.ofNullable(parkingDTO)
                .map(dto -> ResponseEntity.ok((Object) parkingService.getParkingWithDisponibility(dto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cette commune n'existe pas en BDD ou vous l'avez mal écrite"));
    }


    /**
     * Récupérer les informations de stationnement par commune.
     *
     * @param commune La commune pour laquelle les informations de stationnement sont demandées.
     * @return ResponseEntity contenant ParkingDTO si trouvé, ou un statut 404 avec un message d'erreur.
     */
    @GetMapping("/{commune}/pagination")
    public ResponseEntity<?> getParkingWithDisponibility(@PathVariable String commune, @RequestParam(defaultValue = "1") int page,
                                                         @RequestParam(defaultValue = "5") int pageSize) {
        ParkingDTO parkingDTO = parkingService.getParkingByCommune(commune);

        return Optional.ofNullable(parkingDTO)
                .map(dto -> ResponseEntity.ok((Object) parkingService.getParkingWithDisponibility(dto,page,pageSize)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cette commune n'existe pas en BDD ou vous l'avez mal écrite"));
    }

    /**
     * Récupérer la liste des informations de stationnement par commune.
     *
     * @param commune La commune pour laquelle les informations de stationnement sont demandées.
     * @return ResponseEntity contenant une liste de ParkingDTO si trouvé, ou un statut 404 avec un message d'erreur.
     */
    @GetMapping("/list/{commune}")
    public ResponseEntity<?> getListParking(@PathVariable String commune) {
        ParkingDTO parkingDTO = parkingService.getParkingByCommune(commune);

        return Optional.ofNullable(parkingDTO)
                .map(dto -> ResponseEntity.ok((Object) parkingService.getListParking(dto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cette commune n'existe pas en BDD ou vous l'avez mal écrite"));
    }


    /**
     * Récupérer les informations de disponibilité de stationnement par commune.
     *
     * @param commune La commune pour laquelle les informations de disponibilité de stationnement sont demandées.
     * @return ResponseEntity contenant une liste de DisponibilityParking si trouvé, ou un statut 404 avec un message d'erreur.
     */
    @GetMapping("/dispo/{commune}")
    public ResponseEntity<?> getDispoParking(@PathVariable String commune) {
        ParkingDTO parkingDTO = parkingService.getParkingByCommune(commune);

        return Optional.ofNullable(parkingDTO)
                .map(dto -> ResponseEntity.ok((Object) parkingService.getDispoParking(dto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cette commune n'existe pas en BDD ou vous l'avez mal écrite"));
    }

}