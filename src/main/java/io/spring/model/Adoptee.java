package io.spring.model;


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

    @ManyToOne
    Shelter temporaryHome;

}
