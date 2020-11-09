package io.spring.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "zip_code", length = 7)
    Integer zipCode;

    @Column
    String streetlike;

    @Column
    Integer number;
}
