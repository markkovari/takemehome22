package io.spring.util.security;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface JwtService {
    String toToken(String username);

    Optional<String> getSubFromToken(String token);
}
