package io.spring.service.impl;

import io.spring.dto.LoggedInUser;
import io.spring.dto.Login;
import io.spring.dto.Register;
import io.spring.model.User;
import io.spring.repository.UserRepository;
import io.spring.service.UserService;
import io.spring.util.exception.InvalidRequestException;
import io.spring.util.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final
    UserRepository userRepository;

    private final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private final
    JwtService jwtService;


    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
    }

    public void validateRegisterInput(Register register, BindingResult bindingResult) throws InvalidRequestException {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult);
        }
        if (userRepository.findByUsername(register.getUsername()).isPresent()) {
            bindingResult.rejectValue("username", "DUPLICATED", "duplicated username");
        }

        if (userRepository.findByEmail(register.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "DUPLICATED", "duplicated email");
        }

        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public void validateLogin(Login login, BindingResult bindingResult) throws InvalidRequestException {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult);
        }
    }

    @Override
    public LoggedInUser getMatchingLoggedIdUser(Login login) {
        Optional<User> storedUserByEmail = userRepository.findByEmail(login.getEmail());
        if (storedUserByEmail.isPresent() && bCryptPasswordEncoder.matches(login.getPassword(), storedUserByEmail.get().getPassword())) {
            User userData = findById(storedUserByEmail.get().getId()).get();
            return new LoggedInUser(userData, jwtService.toToken(storedUserByEmail.get().getUsername()));
        }
        return null;
    }

    @Override
    public User createAndSave(Register register) {
        User user = new User(
                register.getEmail(),
                register.getUsername(),
                bCryptPasswordEncoder.encode(register.getPassword()));
        return this.userRepository.save(user);
    }
}
