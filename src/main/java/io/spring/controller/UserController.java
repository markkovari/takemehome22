package io.spring.controller;

import io.spring.dto.LoggedInUser;
import io.spring.dto.Login;
import io.spring.dto.Register;
import io.spring.model.User;
import io.spring.service.UserService;
import io.spring.util.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping({"users"})
public class UserController {
    private final
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/", method = POST)
    public ResponseEntity<?> createUser(@Valid @RequestBody Register register, BindingResult bindingResult) {
        userService.validateRegisterInput(register, bindingResult);
        User user = userService.createAndSave(register);
        if (user == null) {
            ResponseEntity.badRequest().body(new Error("Cannot save user"));
        }
        return ResponseEntity.ok().build();
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


