package io.spring.service;

import io.spring.model.Adoptee;
import io.spring.model.Shelter;
import io.spring.util.exception.ShelterAlreadyExistsException;
import io.spring.util.exception.ShelterIsNotEmptyException;

import java.util.List;
import java.util.Optional;

public interface ShelterService {
    Shelter upsertForUser(Shelter shelter) throws ShelterAlreadyExistsException;

    void deleteShelter(Shelter shelter) throws ShelterIsNotEmptyException;

    Optional<Shelter> delete(Shelter shelter);

    Shelter addAdoptee(Shelter shelter, Adoptee adoptee);

    List<Shelter> findAll();
}
