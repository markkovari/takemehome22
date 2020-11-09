package io.spring.service;

import io.spring.dto.LoggedInUser;
import io.spring.dto.Login;
import io.spring.dto.Register;
import io.spring.model.User;
import io.spring.util.exception.InvalidRequestException;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public interface UserService {

    void validateRegisterInput(Register register, BindingResult bindingResult) throws InvalidRequestException;

    Optional<User> findById(Long id);

    void validateLogin(Login login, BindingResult bindingResult) throws InvalidRequestException;

    LoggedInUser getMatchingLoggedIdUser(Login login);

    User createAndSave(Register register);
}
