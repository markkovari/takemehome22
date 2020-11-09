package io.spring.service;

import io.spring.model.Shelter;
import io.spring.model.User;
import io.spring.util.exception.ShelterAlreadyExistsException;

import java.util.Optional;

public interface ShelterService {
    Shelter saveForUser(Shelter shelter, User user) throws ShelterAlreadyExistsException;

    Optional<Shelter> delete(Shelter shelter);

}
