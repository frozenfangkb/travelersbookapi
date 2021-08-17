package com.davidrodriguez.travelersbookapi.controllers;

import com.davidrodriguez.travelersbookapi.models.User;
import com.davidrodriguez.travelersbookapi.services.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Data
@Slf4j
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @GetMapping
    public User currentUser(Authentication authentication) {
        return userService.getCurrentUser(authentication.getName());
    }

    @PostMapping("/validate")
    public User validateUser(@RequestBody User user, Authentication authentication) {
        return userService.validateUser(user, authentication.getName());
    }
}
