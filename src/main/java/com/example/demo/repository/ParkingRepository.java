package com.example.demo.repository;

import com.example.demo.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    Parking findByCommune(String commune);

}
