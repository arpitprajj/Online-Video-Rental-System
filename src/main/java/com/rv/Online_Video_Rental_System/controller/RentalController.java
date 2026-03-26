package com.rv.Online_Video_Rental_System.controller;

import com.rv.Online_Video_Rental_System.entity.Rental;
import com.rv.Online_Video_Rental_System.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Autowired
    RentalService rentalService;

    @PostMapping("/rent/{id}")
    public ResponseEntity<Rental>rentVideo(@PathVariable String id, Principal principal){
        String name= principal.getName();
        Rental rental=rentalService.rentVideo(id,name);
        return ResponseEntity.ok(rental);
    }
    @PatchMapping("/return/{id}")
    public ResponseEntity<Rental>returnVideo(@PathVariable String id,Principal principal){
        Rental rental=rentalService.returnVideo(id,principal.getName());
        return  ResponseEntity.ok(rental);
    }
    @GetMapping
    public ResponseEntity<List<Rental>>getAllRentals(Principal principal){
        String name= principal.getName();
        List<Rental>rentals=rentalService.getAllRentals(name);
        return ResponseEntity.ok(rentals);

    }
    @GetMapping("/active")
    public ResponseEntity<List<Rental>>getActiveRentals(Principal principal){
        String name= principal.getName();
        List<Rental>rentals=rentalService.getActiveRentals(name);
        return ResponseEntity.ok(rentals);
    }


}
