package io.spring.dto;


import io.spring.model.User;
import lombok.Data;
import lombok.Setter;

@Setter
@Data
public class LoggedInUser {
    String token;
    String username;

    public LoggedInUser(User saved, String toToken) {
        this.token = toToken;
        this.username = saved.getUsername();
    }
}
