package com.davidrodriguez.travelersbookapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Expense {
    @Id
    private String id;
    private String title;
    private Double quantity;
    private String currency;
    private String address;
    private String category;
}
