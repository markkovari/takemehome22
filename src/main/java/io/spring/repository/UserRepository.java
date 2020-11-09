package io.spring.repository;

import io.spring.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

}
