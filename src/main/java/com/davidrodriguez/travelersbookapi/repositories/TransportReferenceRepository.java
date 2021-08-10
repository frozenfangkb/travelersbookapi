package com.davidrodriguez.travelersbookapi.repositories;

import com.davidrodriguez.travelersbookapi.models.TransportReference;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransportReferenceRepository extends MongoRepository<TransportReference, String> {
}
