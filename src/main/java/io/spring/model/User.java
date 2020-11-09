package io.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String username;
    private String password;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
