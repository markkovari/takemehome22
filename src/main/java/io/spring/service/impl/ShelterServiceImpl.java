package io.spring.service.impl;

import io.spring.dto.CreateAdoptee;
import io.spring.model.Adoptee;
import io.spring.model.Shelter;
import io.spring.model.User;
import io.spring.repository.AdopteeRepository;
import io.spring.repository.ShelterRepository;
import io.spring.repository.UserRepository;
import io.spring.service.ShelterService;
import io.spring.util.exception.ShelterAlreadyExistsException;
import io.spring.util.exception.ShelterIsNotEmptyException;
import io.spring.util.exception.UserHasNoShelterYetException;
import io.spring.util.security.AuthorizationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;
    private final UserRepository userRepository;
    private final AdopteeRepository adopteeRepository;
    private final AuthorizationService authorizationService;

    public ShelterServiceImpl(ShelterRepository shelterRepository,
                              UserRepository userRepository,
                              AdopteeRepository adopteeRepository,
                              AuthorizationService authorizationService) {
        this.shelterRepository = shelterRepository;
        this.userRepository = userRepository;
        this.adopteeRepository = adopteeRepository;
        this.authorizationService = authorizationService;
    }

    @Override
    public Shelter upsertForUser(Shelter shelter) throws ShelterAlreadyExistsException {
        User user = authorizationService.getUserByLoggedIn().orElse(null);
        if (user == null) {
            throw new Error("Cannot find user");
        }
        if (user.getShelter() != null) {
            throw new ShelterAlreadyExistsException("Shelter already exists for: " + user.getUsername());
        }
        if (shelter == null || shelter.getId() == null) {
            shelter = new Shelter();
        }
        shelter.setOwner(user);
        user.setShelter(shelter);
        Shelter sh = shelterRepository.save(shelter);
        userRepository.save(user);
        return sh;
    }

    @Override
    public void deleteShelter(Shelter shelter) throws ShelterIsNotEmptyException {
        if (shelter.getAdoptees().size() > 0) {
            throw new ShelterIsNotEmptyException("Shelter is not empty unfortunately, please take care of the adoptees first");
        }
        shelterRepository.delete(shelter);
    }

    @Override
    public Optional<Shelter> delete(Shelter shelter) {
        return Optional.empty();
    }

    @Override
    public Adoptee addAdoptee(CreateAdoptee newAdoptee) throws UserHasNoShelterYetException {
        User user = authorizationService.getUserByLoggedIn().orElse(null);
        if (user == null || user.getShelter() == null) {
            throw new UserHasNoShelterYetException("User has no shelter registered yet");
        }
        Shelter toRegister = user.getShelter();
        List<Adoptee> adoptees = toRegister.getAdoptees();
        Adoptee adoptee = new Adoptee(newAdoptee);
        adoptees.add(adoptee);
        toRegister.setAdoptees(adoptees);
        adoptee.setTemporaryHome(toRegister);
        Adoptee saved = adopteeRepository.save(adoptee);
        shelterRepository.save(toRegister);
        return saved;
    }

    @Override
    public List<Shelter> findAll() {
        return (List<Shelter>) shelterRepository.findAll();
    }
}
