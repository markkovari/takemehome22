package io.spring.controller;

import io.spring.dto.LoggedInUser;
import io.spring.dto.Login;
import io.spring.dto.Register;
import io.spring.model.Adoptee;
import io.spring.model.User;
import io.spring.service.AdopteeService;
import io.spring.service.UserService;
import io.spring.util.exception.InvalidRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping({"users"})
public class UserController {
    private final
    UserService userService;
    private final
    AdopteeService adopteeService;

    public UserController(UserService userService, AdopteeService adopteeService) {
        this.userService = userService;
        this.adopteeService = adopteeService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> createUser(@Valid @RequestBody Register register, BindingResult bindingResult) {
        userService.validateRegisterInput(register, bindingResult);
        User user = userService.createAndSave(register);
        if (user == null) {
            ResponseEntity.badRequest().body(new Error("Cannot save user"));
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody Login login, BindingResult bindingResult) {
        userService.validateLogin(login, bindingResult);
        LoggedInUser loggedInUser = userService.getMatchingLoggedIdUser(login);
        if (loggedInUser == null) {
            bindingResult.rejectValue("password", "INVALID", "invalid email or password");
            throw new InvalidRequestException(bindingResult);
        }
        return ResponseEntity.ok(loggedInUser);
    }

    @PostMapping(path = "togglelikeadoptee/{id}")
    public ResponseEntity<?> toggleAdopteeById(@PathVariable(name = "id") Long id) {
        try {
            Adoptee toggled = adopteeService.toggleFavourite(id);
            return ResponseEntity.ok(toggled);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}


