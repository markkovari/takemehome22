package io.spring.service.impl;

import io.spring.model.Shelter;
import io.spring.model.User;
import io.spring.repository.ShelterRepository;
import io.spring.repository.UserRepository;
import io.spring.service.ShelterService;
import io.spring.util.exception.ShelterAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ShelterServiceImpl implements ShelterService {
    
    private final ShelterRepository shelterRepository;
    private final UserRepository userRepository;

    public ShelterServiceImpl(ShelterRepository shelterRepository, UserRepository userRepository) {
        this.shelterRepository = shelterRepository;
        this.userRepository = userRepository;
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
    public Optional<Shelter> delete(Shelter shelter) {
        return Optional.empty();
    }
}
