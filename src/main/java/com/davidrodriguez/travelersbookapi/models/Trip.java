package com.davidrodriguez.travelersbookapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document @Data @NoArgsConstructor
public class Trip {
    @Id
    private String id;
    private String name;
    private String destination;
    private String lodgingAddress;
    @DBRef
    private List<TransportReference> transportReferences;
    private LocalDate initialDate;
    private LocalDate endDate;
    @DBRef
    private List<Member> members;
    private String mainImage;
    @DBRef
    private List<Day> days;
    @DBRef
    private User owner;
    private boolean isPublished;
}
