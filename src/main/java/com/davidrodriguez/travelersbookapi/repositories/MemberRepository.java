package com.davidrodriguez.travelersbookapi.repositories;

import com.davidrodriguez.travelersbookapi.models.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member, String> {
    Member findMemberByName(String name);
    boolean existsMemberByName(String name);
}
