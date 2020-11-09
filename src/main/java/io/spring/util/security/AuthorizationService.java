package io.spring.util.security;

import io.spring.model.User;
import io.spring.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public static boolean canWriteArticle(User user, Article article) {
//        return user.getId().equals(article.getUserId());
//    }
//
//    public static boolean canWriteComment(User user, Article article, Comment comment) {
//        return user.getId().equals(article.getUserId()) || user.getId().equals(comment.getUserId());
//    }

    public Optional<User> getUserByLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Optional.of((User) auth.getPrincipal());
    }
}
