package io.spring.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shelter {

    @Id
    @GeneratedValue
    Long id;

    @OneToOne
    User owner;

    @OneToOne
    Address address;

    @OneToMany(fetch = FetchType.EAGER)
    List<Adoptee> adoptees;

}
