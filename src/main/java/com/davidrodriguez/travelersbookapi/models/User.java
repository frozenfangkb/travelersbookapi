package com.davidrodriguez.travelersbookapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document @Data @NoArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private String username;
    @Indexed(unique = true)
    private String email;
}
