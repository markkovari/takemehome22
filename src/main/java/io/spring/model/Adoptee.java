package io.spring.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.spring.dto.CreateAdoptee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Adoptee {

    @Id
    @GeneratedValue
    Long id;

    String name;

    String color;

    @JsonIgnore
    @ManyToOne
    Shelter temporaryHome;

    @ManyToMany(fetch = FetchType.EAGER)
    List<User> favouriteBy;

    public Adoptee(CreateAdoptee createAdoptee) {
        this.color = createAdoptee.getColor();
        this.name = createAdoptee.getName();
    }
}
