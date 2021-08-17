package com.davidrodriguez.travelersbookapi.controllers;

import com.davidrodriguez.travelersbookapi.helpers.FileUploadUtil;
import com.davidrodriguez.travelersbookapi.models.NewTripStructure;
import com.davidrodriguez.travelersbookapi.models.Trip;
import com.davidrodriguez.travelersbookapi.services.TripService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/trips")
@Data
@Slf4j
@CrossOrigin(origins = "*")
public class TripController {
    private final TripService tripService;

    @GetMapping("/")
    public ResponseEntity<List<Trip>> getAllTrips() {
        return ResponseEntity.ok().body(tripService.getAllTrips());
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<Trip> getTripById(@PathVariable(value = "tripId") String tripId) {
        return ResponseEntity.ok().body(tripService.getTripById(tripId));
    }

    @GetMapping("/own")
    public ResponseEntity<List<Trip>> getOwnTrips(Authentication authentication) {
        return ResponseEntity.ok().body(tripService.getOwnTrips(authentication.getName()));
    }

    @PostMapping("/newTrip")
    public ResponseEntity<String> saveNewTrip(@RequestBody NewTripStructure newTrip, Authentication authentication) {
        return ResponseEntity.ok(tripService.saveTrip(newTrip, authentication.getName()));
    }

    @PostMapping("/uploadImage/{tripId}")
    public ResponseEntity<String> uploadTripImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable(value = "tripId") String tripId
    ) throws IOException
    {
        tripService.uploadTripImage(image, tripId);
        return ResponseEntity.ok("Image uploaded OK");
    }
}
