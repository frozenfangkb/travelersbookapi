package com.davidrodriguez.travelersbookapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Member {
    @Id
    private String id;
    private String name;
    private String userId;
}
