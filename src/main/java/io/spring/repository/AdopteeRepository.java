package io.spring.repository;

import io.spring.model.Adoptee;
import org.springframework.data.repository.CrudRepository;

public interface AdopteeRepository extends CrudRepository<Adoptee, Long> {
}
