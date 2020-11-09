package io.spring.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    List<Adoptee> adoptees = new ArrayList<>();

}
