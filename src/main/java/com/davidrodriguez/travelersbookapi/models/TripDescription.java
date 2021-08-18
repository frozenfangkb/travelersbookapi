package com.davidrodriguez.travelersbookapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Data
@NoArgsConstructor
public class TripDescription {
    @Id
    private String id;
    private String smallDescription;
    private String description;
    @LastModifiedDate
    private Instant updatedAt;
}
