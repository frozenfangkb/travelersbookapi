package com.davidrodriguez.travelersbookapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
@Data
@NoArgsConstructor
public class Day {
    @Id
    private String id;
    private String title;
    private LocalDate date;
    private String mainImage;
    @DBRef
    private List<Expense> expenses;
    private List<String> photos;
    private List<String> videos;
    private List<String> visitedPlaces;
}
