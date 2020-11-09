package io.spring.repository;

import io.spring.model.Shelter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends CrudRepository<Shelter, Long> {
}
