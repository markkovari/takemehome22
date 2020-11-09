package io.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @JsonIgnore
    @OneToOne
    Shelter shelter;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    List<Adoptee> favourites;

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String username;
    @JsonIgnore
    private String password;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
