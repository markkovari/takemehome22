package io.spring.service.impl;

import io.spring.model.Adoptee;
import io.spring.model.User;
import io.spring.repository.AdopteeRepository;
import io.spring.repository.UserRepository;
import io.spring.service.AdopteeService;
import io.spring.util.exception.CannotFavouriteOwnedAdopteeException;
import io.spring.util.exception.NoSuchAdopteeException;
import io.spring.util.security.AuthorizationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdopteeServiceImpl implements AdopteeService {

    private final AdopteeRepository adopteeRepository;
    private final AuthorizationService authorizationService;
    private final UserRepository userRepository;

    public AdopteeServiceImpl(AdopteeRepository adopteeRepository, AuthorizationService authorizationService, UserRepository userRepository) {
        this.adopteeRepository = adopteeRepository;
        this.authorizationService = authorizationService;
        this.userRepository = userRepository;
    }

    @Override
    public List<Adoptee> getAll() {
        return (List<Adoptee>) adopteeRepository.findAll();
    }

    @Override
    public Adoptee toggleFavourite(Long id) throws CannotFavouriteOwnedAdopteeException, NoSuchAdopteeException {
        User user = authorizationService.getUserByLoggedIn().get();
        Adoptee adoptee = adopteeRepository.findById(id).orElse(null);
        if (adoptee == null) {
            throw new NoSuchAdopteeException("Adoptee does not exists with id: " + id);
        }
        if (user.getShelter() == null || user.getShelter().getAdoptees().stream().noneMatch(a -> a.getId().equals(id))) {
            List<Adoptee> favourites = user.getFavourites();
            favourites.add(adoptee);
            userRepository.save(user);
            List<User> favouriteBy = adoptee.getFavouriteBy();
            favouriteBy.add(user);
            return adopteeRepository.save(adoptee);
        }
        throw new CannotFavouriteOwnedAdopteeException("You cannot mark animal as favourite (but you still can pet it) with id: " + id);
    }
}
