package io.spring.service;

import io.spring.dto.CreateAdoptee;
import io.spring.model.Adoptee;
import io.spring.model.Shelter;
import io.spring.util.exception.ShelterAlreadyExistsException;
import io.spring.util.exception.ShelterIsNotEmptyException;
import io.spring.util.exception.UserHasNoShelterYetException;

import java.util.List;
import java.util.Optional;

public interface ShelterService {
    Shelter upsertForUser(Shelter shelter) throws ShelterAlreadyExistsException;

    void deleteShelter(Shelter shelter) throws ShelterIsNotEmptyException;

    Optional<Shelter> delete(Shelter shelter);

    Adoptee addAdoptee(CreateAdoptee adoptee) throws UserHasNoShelterYetException;

    List<Shelter> findAll();
}
