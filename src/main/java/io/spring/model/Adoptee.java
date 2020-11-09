package io.spring.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.spring.dto.CreateAdoptee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

    public Adoptee(CreateAdoptee createAdoptee) {
        this.color = createAdoptee.getColor();
        this.name = createAdoptee.getName();
    }
}
