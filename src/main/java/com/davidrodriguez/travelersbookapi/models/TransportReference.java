package com.davidrodriguez.travelersbookapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
@NoArgsConstructor
public class TransportReference {
    @Id
    private String id;
    private String transportType;
    private String referenceId;
    private String company;
    private LocalDateTime initialDatetime;
    private LocalDateTime endDateTime;
    private List<String> seats;
}
