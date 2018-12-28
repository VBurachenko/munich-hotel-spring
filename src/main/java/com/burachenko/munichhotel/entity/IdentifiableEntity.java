package com.burachenko.munichhotel.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class IdentifiableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long id;

}
