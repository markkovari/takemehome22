package io.spring.service;

import io.spring.model.Adoptee;
import io.spring.util.exception.CannotFavouriteOwnedAdopteeException;
import io.spring.util.exception.NoSuchAdopteeException;

import java.util.List;

public interface AdopteeService {

    List<Adoptee> getAll();

    Adoptee toggleFavourite(Long id) throws CannotFavouriteOwnedAdopteeException, NoSuchAdopteeException;
}
