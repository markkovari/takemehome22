package io.spring.service.impl;

import io.spring.model.Adoptee;
import io.spring.model.Shelter;
import io.spring.model.User;
import io.spring.repository.AdopteeRepository;
import io.spring.repository.ShelterRepository;
import io.spring.repository.UserRepository;
import io.spring.service.ShelterService;
import io.spring.util.exception.ShelterAlreadyExistsException;
import io.spring.util.exception.ShelterIsNotEmptyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;
    private final UserRepository userRepository;
    private final AdopteeRepository adopteeRepository;

    public ShelterServiceImpl(ShelterRepository shelterRepository, UserRepository userRepository, AdopteeRepository adopteeRepository) {
        this.shelterRepository = shelterRepository;
        this.userRepository = userRepository;
        this.adopteeRepository = adopteeRepository;
    }


    @Override
    public Shelter saveForUser(Shelter shelter, User user) throws ShelterAlreadyExistsException {
        if (user.getShelter() != null) {
            throw new ShelterAlreadyExistsException("Shelter already exists for: " + user.getUsername());
        }
        shelter.setOwner(user);
        user.setShelter(shelter);
        userRepository.save(user);
        return shelterRepository.save(shelter);
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
    public Shelter addAdoptee(Shelter shelter, Adoptee newAdoptee) {
        List<Adoptee> adoptees = shelter.getAdoptees();
        adoptees.add(newAdoptee);
        shelter.setAdoptees(adoptees);
        adopteeRepository.save(newAdoptee);
        return shelterRepository.save(shelter);
    }

    @Override
    public List<Shelter> findAll() {
        return (List<Shelter>) shelterRepository.findAll();
    }
}
