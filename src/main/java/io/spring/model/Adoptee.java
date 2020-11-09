package io.spring.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
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
