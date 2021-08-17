package com.davidrodriguez.travelersbookapi.services;

import com.davidrodriguez.travelersbookapi.models.User;
import com.davidrodriguez.travelersbookapi.repositories.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service @Data
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    @Override
    public User validateUser(User user, String userId) {
        if (user.getUsername() != null && userRepository.existsByUsername(user.getUsername())) {
            return userRepository.findUserByUsername(user.getUsername());
        } else {
            User newUser = new User();
            newUser.setName(user.getName());
            newUser.setUsername(userId);
            newUser.setEmail(user.getEmail());
            newUser.setProfileImage(user.getProfileImage());
            userRepository.insert(newUser);

            return newUser;
        }
    }

    @Override
    public User getCurrentUser(String id) {
        return userRepository.existsByUsername(id)
                ? userRepository.findUserByUsername(id)
                : null;
    }
}
