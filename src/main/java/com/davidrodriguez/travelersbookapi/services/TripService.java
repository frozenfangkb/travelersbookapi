package com.davidrodriguez.travelersbookapi.services;

import com.davidrodriguez.travelersbookapi.helpers.FileUploadUtil;
import com.davidrodriguez.travelersbookapi.models.*;
import com.davidrodriguez.travelersbookapi.repositories.DayRepository;
import com.davidrodriguez.travelersbookapi.repositories.MemberRepository;
import com.davidrodriguez.travelersbookapi.repositories.TripRepository;
import com.davidrodriguez.travelersbookapi.repositories.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public Trip getTripById(String id) {
        return tripRepository.findTripById(id);
    }

    @Override
    public List<Trip> getOwnTrips(String ownerId) {
        User user = userRepository.findUserByUsername(ownerId);

        return tripRepository.findTripsByOwnerId(user.getId());
    }

    @Override
    public Trip getTrip(String id) {
        return tripRepository.findTripById(id);
    }

    @Override
    public String saveTrip(
            NewTripStructure trip,
            String userId
    ) {
        // TODO: Check the whole method when JWT is implemented in order to change the user part
        Trip newTrip = new Trip();
        tripRepository.insert(newTrip);
        User owner = userRepository.findUserByUsername(userId);
        List<Member> tripMembers = new ArrayList<>();

        trip.getMembers().forEach(member -> {
            if(!memberRepository.existsMemberByName(member)) {
                Member tempMember = new Member();
                tempMember.setName(member);
                memberRepository.insert(tempMember);
                tripMembers.add(tempMember);
            }
        });

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

    @Override
    public void uploadTripImage(
            MultipartFile image,
            String tripId
    ) throws IOException
    {
        // TODO: Check for older images and delete them if exist
        if(tripRepository.existsById(tripId)) {
            Trip trip = tripRepository.findTripById(tripId);
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));

            trip.setMainImage(fileName);
            tripRepository.save(trip);

            String uploadDir = "trip-images/" + trip.getId();

            FileUploadUtil.saveFile(uploadDir, fileName, image);
        } else {
            throw new IllegalStateException("The requested trip wasn't found");
        }
    }
}
