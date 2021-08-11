package com.davidrodriguez.travelersbookapi.services;

import com.davidrodriguez.travelersbookapi.models.Member;
import com.davidrodriguez.travelersbookapi.models.Trip;
import com.davidrodriguez.travelersbookapi.repositories.MemberRepository;
import com.davidrodriguez.travelersbookapi.repositories.TripRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service @Data @Transactional
public class TripService implements TripServiceInterface {
    private final TripRepository tripRepository;
    private MemberRepository memberRepository;

    @Override
    public List<Trip> getAllTrips() {
        return tripRepository.findAll(); // TODO: Paginate this response
    }

    @Override
    public List<Trip> getOwnTrips(String ownerId) {
        return tripRepository.findTripsByOwnerId(ownerId);
    }

    @Override
    public Trip getTrip(String id) {
        return tripRepository.findTripById(id);
    }

    @Override
    public String saveTrip(
            String name,
            String destination,
            String lodgingAddress,
            LocalDate initialDate,
            LocalDate endDate,
            List<String> members,
            String mainImage
    ) {
        // TODO: Check the whole method when JWT is implemented in order to change the user part
        Trip newTrip = new Trip();
        List<Member> tripMembers = new ArrayList<>();

        members.forEach(member -> {
            if(!memberRepository.existsMemberByName(member)) {
                Member tempMember = new Member();
                tempMember.setName(member);
            }
        });

        newTrip.setName(name);
        newTrip.setDestination(destination);
        newTrip.setLodgingAddress(lodgingAddress);
        newTrip.setInitialDate(initialDate);
        newTrip.setEndDate(endDate);

        return null;
    }

    @Override
    public void deleteTrip(String id) {
        // TODO: Check for user ownership of the trip and authorization when jwt is implemented
        tripRepository.deleteById(id);
    }

    @Override
    public Trip updateTrip(Trip trip) {
        return null;
    }
}
