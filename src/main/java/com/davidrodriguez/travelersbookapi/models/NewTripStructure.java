package com.davidrodriguez.travelersbookapi.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class NewTripStructure {
    private String name;
    private String destination;
    private String lodgingAddress;
    private LocalDate initialDate;
    private LocalDate endDate;
    private List<String> members;
}
