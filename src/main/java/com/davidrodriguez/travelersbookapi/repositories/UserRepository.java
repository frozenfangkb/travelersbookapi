package com.davidrodriguez.travelersbookapi.repositories;

import com.davidrodriguez.travelersbookapi.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserById(String id);
}
