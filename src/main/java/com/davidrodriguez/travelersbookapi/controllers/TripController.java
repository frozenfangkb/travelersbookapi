package com.davidrodriguez.travelersbookapi.controllers;

import com.davidrodriguez.travelersbookapi.models.NewTripStructure;
import com.davidrodriguez.travelersbookapi.models.Trip;
import com.davidrodriguez.travelersbookapi.services.TripService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@Data
@Slf4j
public class TripController {
    private final TripService tripService;

    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> getAllTrips() {
        return ResponseEntity.ok().body(tripService.getAllTrips());
    }

    @GetMapping("/trips/{tripId}")
    public ResponseEntity<Trip> getTripById(@PathVariable(value = "tripId") String tripId) {
        log.info(tripId);
        return ResponseEntity.ok().body(tripService.getTripById(tripId));
    }

    @PostMapping("/newTrip")
    public ResponseEntity<String> saveNewTrip(@RequestBody NewTripStructure newTrip) {
        return ResponseEntity.ok(tripService.saveTrip(newTrip));
    }
}
