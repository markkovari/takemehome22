package io.spring.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shelter {

    @Id
    @GeneratedValue
    Long id;

    @OneToOne
    User owner;

    @OneToMany
    List<Shelter> shelters;

    @OneToOne
    Address address;

}
