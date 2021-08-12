package com.davidrodriguez.travelersbookapi.controllers;

import com.davidrodriguez.travelersbookapi.models.NewTripStructure;
import com.davidrodriguez.travelersbookapi.models.Trip;
import com.davidrodriguez.travelersbookapi.services.TripService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@Data
public class TripController {
    private final TripService tripService;

    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> getAllTrips() {
        return ResponseEntity.ok().body(tripService.getAllTrips());
    }

    @PostMapping("/newTrip")
    public ResponseEntity<String> saveNewTrip(@RequestBody NewTripStructure newTrip) {
        return ResponseEntity.ok(tripService.saveTrip(newTrip));
    }
}
