package com.davidrodriguez.travelersbookapi.repositories;

import com.davidrodriguez.travelersbookapi.models.TripDescription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TripDescriptionRepository extends MongoRepository<TripDescription, String> {
    TripDescription findTripDescriptionById(String id);
    boolean existsTripDescriptionById(String id);
}
