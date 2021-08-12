package com.davidrodriguez.travelersbookapi.services;

import com.davidrodriguez.travelersbookapi.models.*;
import com.davidrodriguez.travelersbookapi.repositories.DayRepository;
import com.davidrodriguez.travelersbookapi.repositories.MemberRepository;
import com.davidrodriguez.travelersbookapi.repositories.TripRepository;
import com.davidrodriguez.travelersbookapi.repositories.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service @Data @Transactional
public class TripService implements TripServiceInterface {
    private final TripRepository tripRepository;
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private final DayRepository dayRepository;

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
            NewTripStructure trip
    ) {
        // TODO: Check the whole method when JWT is implemented in order to change the user part
        Trip newTrip = new Trip();
        tripRepository.insert(newTrip);
        List<Member> tripMembers = new ArrayList<>();

        trip.getMembers().forEach(member -> {
            if(!memberRepository.existsMemberByName(member)) {
                Member tempMember = new Member();
                tempMember.setName(member);
                memberRepository.insert(tempMember);
            }
        });

        // TODO: Change all this part and get the user from the auth instead of this
        User owner = new User();
        owner.setName("Test user");
        owner.setEmail("test@example.com");
        owner.setUsername("teeeest");
        userRepository.insert(owner);

        int daysToGenerate = (int)DAYS.between(trip.getInitialDate(), trip.getEndDate());
        List<Day> tripDays = new ArrayList<>();

        for (int i = 1; i <= daysToGenerate; i++) {
            LocalDate tempDate = LocalDate.of(
                    trip.getInitialDate().getYear(),
                    trip.getInitialDate().getMonth(),
                    trip.getInitialDate().getDayOfMonth()
            );
            if (i > 1) {
                tempDate = tempDate.plusDays(i-1);
            }
            Day tempDay = new Day();
            tempDay.setDate(tempDate);
            tempDay.setTripRef(newTrip.getId());
            dayRepository.insert(tempDay);
            tripDays.add(tempDay);
        }

        newTrip.setName(trip.getName());
        newTrip.setDestination(trip.getDestination());
        newTrip.setLodgingAddress(trip.getLodgingAddress());
        newTrip.setInitialDate(trip.getInitialDate());
        newTrip.setEndDate(trip.getEndDate());
        newTrip.setMembers(tripMembers);
        newTrip.setPublished(false);
        newTrip.setOwner(owner);
        newTrip.setDays(tripDays);

        tripRepository.save(newTrip);

        return newTrip.getId();
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
