package com.davidrodriguez.travelersbookapi.services;

import com.davidrodriguez.travelersbookapi.models.User;

public interface UserServiceInterface {
    public User validateUser(User user, String userId);
    public User getCurrentUser(String name);
}
