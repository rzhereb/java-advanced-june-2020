package com.oktenweb.javaadvjune.controller;

import com.oktenweb.javaadvjune.dto.AuthRequest;
import com.oktenweb.javaadvjune.dto.AuthenticationResponse;
import com.oktenweb.javaadvjune.entity.User;
import com.oktenweb.javaadvjune.service.IUserService;
import com.oktenweb.javaadvjune.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
// Цей контролер не має жодного відношення до автентифікації. Нею повністю займається Spring
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // реєстрація юзера
    @PostMapping
    public String registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse generateJWT(@RequestBody AuthRequest authRequest) {
        authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        return new AuthenticationResponse(jwtService.generateToken(authRequest.getUsername(), "bla bla"));
    }
}
