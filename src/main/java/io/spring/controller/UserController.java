package io.spring.controller;

import io.spring.dto.LoggedInUser;
import io.spring.dto.Login;
import io.spring.dto.Register;
import io.spring.repository.UserRepository;
import io.spring.service.UserService;
import io.spring.util.exception.InvalidRequestException;
import io.spring.util.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping({"users"})
public class UserController {
    private UserRepository userRepository;
    private UserService userService;
    private JwtService jwtService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserRepository userRepository,
                          UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder,
                          JwtService jwtService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
    }

    @RequestMapping(path = "/", method = POST)
    public ResponseEntity<?> createUser(@Valid @RequestBody Register register, BindingResult bindingResult) {
        userService.validateRegisterInput(register, bindingResult);
        userService.createAndSave(register);
        return ResponseEntity.status(201).build();
    }

    @RequestMapping(path = "/login", method = POST)
    public ResponseEntity<?> userLogin(@Valid @RequestBody Login login, BindingResult bindingResult) {
        userService.validateLogin(login, bindingResult);
        LoggedInUser loggedInUser = userService.getMatchingLoggedIdUser(login);
        if (loggedInUser == null) {
            bindingResult.rejectValue("password", "INVALID", "invalid email or password");
            throw new InvalidRequestException(bindingResult);
        }
        return ResponseEntity.ok(loggedInUser);
    }

}


